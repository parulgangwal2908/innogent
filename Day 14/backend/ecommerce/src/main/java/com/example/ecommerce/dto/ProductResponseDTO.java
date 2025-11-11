package com.example.ecommerce.dto;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponseDTO {
    private Long id;
    private String title;
    private String description;
    private String category;
    private double price;
    private String imageUrl;
    private Integer ratingCount;
    private Double ratingRate;
}
