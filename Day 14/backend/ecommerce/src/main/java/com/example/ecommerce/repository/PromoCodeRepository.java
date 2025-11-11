package com.example.ecommerce.repository;

import com.example.ecommerce.model.PromoCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

public interface PromoCodeRepository extends JpaRepository<PromoCode,Long> {
    Optional<PromoCode>findByCodeIgnoreCase(String code);
}

