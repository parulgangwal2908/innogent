package com.example.ecommerce.service;

import com.example.ecommerce.dto.ProductRequestDTO;
import com.example.ecommerce.model.Product;
import com.example.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> searchProducts(String keyword) {
        return productRepository.findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(keyword, keyword);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with ID: " + id));
    }

    public List<Product> getProductsByCategory(String category) {
        return productRepository.findByCategoryIgnoreCase(category);
    }

    public List<Product> addProducts(List<ProductRequestDTO> productDTOs) {
        List<Product> products = new ArrayList<>();

        for (ProductRequestDTO dto : productDTOs) {
            Product p = new Product();
            p.setTitle(dto.getTitle());
            p.setDescription(dto.getDescription());
            p.setCategory(dto.getCategory());
            p.setImage(dto.getImage());
            p.setPrice(dto.getPrice());
            if (dto.getRating() != null) {
                p.setRatingCount(dto.getRating().getCount());
                p.setRatingRate(dto.getRating().getRate());
            }

            products.add(p);
        }

        return productRepository.saveAll(products);
    }

}
