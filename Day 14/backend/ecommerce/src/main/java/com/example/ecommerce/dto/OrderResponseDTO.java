package com.example.ecommerce.dto;

import com.example.ecommerce.model.Address;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderResponseDTO {
    private Long orderId;
    private Address address;
    private double totalAmount;
    private String status;
    private List<OrderItemResponseDTO> items;
}
