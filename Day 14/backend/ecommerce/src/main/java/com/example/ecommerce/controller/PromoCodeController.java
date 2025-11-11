package com.example.ecommerce.controller;

import com.example.ecommerce.model.PromoCode;
import com.example.ecommerce.repository.PromoCodeRepository;
import com.example.ecommerce.service.PromoCodeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/promos")
@CrossOrigin(origins = "http://localhost:5173")
public class PromoCodeController {

    private final PromoCodeService promoCodeService;
    private final PromoCodeRepository promoCodeRepository;

    public PromoCodeController(PromoCodeService promoCodeService, PromoCodeRepository promoCodeRepository) {
        this.promoCodeService = promoCodeService;
        this.promoCodeRepository = promoCodeRepository;
    }

    @GetMapping("/validate/{code}")
    public ResponseEntity<?> validatePromo(@PathVariable String code) {
        try {
            PromoCode promo = promoCodeService.validatePromo(code);
            return ResponseEntity.ok(promo);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public List<PromoCode> getAllPromos() {
        return promoCodeRepository.findAll();
    }

    @PostMapping
    public List<PromoCode> addPromos(@RequestBody List<PromoCode> promos) {
        return promoCodeRepository.saveAll(promos);
    }
}
