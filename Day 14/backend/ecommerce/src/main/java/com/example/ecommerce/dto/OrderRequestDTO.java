package com.example.ecommerce.dto;

import lombok.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class OrderRequestDTO {
    private String customerName;
    private String phone;
    private String address;
    private String city;
    private String state;
    private String pincode;
    private String promoCode;
    private List<OrderItemRequestDTO> items;

}
