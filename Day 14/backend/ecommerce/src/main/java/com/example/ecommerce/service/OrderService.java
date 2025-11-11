package com.example.ecommerce.service;

import com.example.ecommerce.dto.*;
import com.example.ecommerce.model.*;
import com.example.ecommerce.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private PromoCodeService promoCodeService;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    public OrderResponseDTO createOrder(OrderRequestDTO dto) {
        double total = 0.0;
        List<OrderItem> orderItems = new ArrayList<>();

        for (OrderItemRequestDTO itemDTO : dto.getItems()) {
            Product product = productRepository.findById(itemDTO.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            double itemTotal = product.getPrice() * itemDTO.getQuantity();
            total += itemTotal;

            OrderItem item = new OrderItem();
            item.setProductId(product.getId());
            item.setProductTitle(product.getTitle());
            item.setProductImage(product.getImage());
            item.setUnitPrice(product.getPrice());
            item.setQuantity(itemDTO.getQuantity());
            item.setTotalPrice(itemTotal);
            orderItems.add(item);
        }
        if (dto.getPromoCode() != null && !dto.getPromoCode().isBlank()) {
            PromoCode promo = promoCodeService.validatePromo(dto.getPromoCode());
            if (promo.getDiscountPercent() != null) {
                total -= total * (promo.getDiscountPercent() / 100);
            } else if (promo.getDiscountAmount() != null) {
                total -= promo.getDiscountAmount();
            }
            if (total < 0) total = 0;
        }

        Address address = new Address(
                dto.getCustomerName(),
                dto.getPhone(),
                dto.getAddress(),
                dto.getCity(),
                dto.getState(),
                dto.getPincode()
        );

        Order order = new Order();
        order.setAddress(address);
        order.setTotalAmount(total);
        order.setStatus(OrderStatus.PENDING);
        order.setPlacedAt(LocalDateTime.now());
        Order savedOrder = orderRepository.save(order);

        for (OrderItem item : orderItems) {
            item.setOrder(savedOrder);
        }
        orderItemRepository.saveAll(orderItems);

        List<OrderItemResponseDTO> itemResponses = orderItems.stream()
                .map(i -> new OrderItemResponseDTO(
                        i.getId(),
                        i.getProductTitle(),
                        i.getProductImage(),
                        i.getQuantity(),
                        i.getTotalPrice()
                ))
                .collect(Collectors.toList());

        return new OrderResponseDTO(
                savedOrder.getId(),
                savedOrder.getAddress(),
                savedOrder.getTotalAmount(),
                savedOrder.getStatus().toString(),
                itemResponses
        );
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public List<Order> getOrdersByStatus(OrderStatus status) {
        return orderRepository.findByStatus(status);
    }

    public void deleteOrder(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        orderItemRepository.deleteAll(order.getOrderItems());
        orderRepository.delete(order);
    }
}
