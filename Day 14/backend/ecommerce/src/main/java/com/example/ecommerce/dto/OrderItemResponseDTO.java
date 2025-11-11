package com.example.ecommerce.dto;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemResponseDTO {
    private Long id;
    private String productTitle;
    private String productImage;
    private int quantity;
    private double totalPrice;

}
