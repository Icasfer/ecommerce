package com.ecommerce.pricing.infrastructure.controller;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.pricing.application.dto.PricesResponseDTO;
import com.ecommerce.pricing.application.port.in.GetProductPriceUseCase;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/prices")
@RequiredArgsConstructor
public class PriceController {
     private final GetProductPriceUseCase getProductPriceUseCase;

    @GetMapping
    public Optional<PricesResponseDTO> getPrice(
            @RequestParam Integer brandId,
            @RequestParam Long productId,
            @RequestParam String applicationDate) {
        return getProductPriceUseCase.getProductPrice(brandId, productId, LocalDateTime.parse(applicationDate));
    }
}