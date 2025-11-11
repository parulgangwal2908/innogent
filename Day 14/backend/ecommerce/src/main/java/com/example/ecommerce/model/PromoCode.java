package com.example.ecommerce.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "promo_codes", indexes = @Index(columnList = "code", name = "idx_promo_code"))
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PromoCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String code;

    private Double discountPercent;

     private Double discountAmount;

    private Double minOrderAmount;

    private LocalDateTime validFrom;
    private LocalDateTime validTill;

    private boolean active = true;
}