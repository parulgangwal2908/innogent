package com.example.ecommerce.service;

import com.example.ecommerce.model.PromoCode;
import com.example.ecommerce.repository.PromoCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class PromoCodeService {

    @Autowired
    private PromoCodeRepository promoCodeRepository;

    public PromoCode validatePromo(String code) {
        Optional<PromoCode> promoOpt = promoCodeRepository.findByCodeIgnoreCase(code);

        if (promoOpt.isEmpty()) {
            throw new RuntimeException("Invalid promo code");
        }

        PromoCode promo = promoOpt.get();

        if (promo.getValidFrom() == null || promo.getValidTill() == null) {
            throw new RuntimeException("Promo code validity period not set");
        }

        LocalDate today = LocalDate.now();
        LocalDate start = promo.getValidFrom().toLocalDate();
        LocalDate end = promo.getValidTill().toLocalDate();

        if (today.isBefore(start) || today.isAfter(end)) {
            throw new RuntimeException("Promo code expired or inactive");
        }

        if (!promo.isActive()) {
            throw new RuntimeException("Promo code is not active");
        }

        return promo;
    }
}
