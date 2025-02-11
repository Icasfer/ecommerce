package com.ecommerce.pricing.infrastructure.adapter.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ecommerce.pricing.domain.repository.PriceRepository;
import com.ecommerce.pricing.domain.model.Price;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PriceRepositoryAdapter implements PriceRepository {
    private final JpaPriceRepository jpaPriceRepository;

    @Override
    public List<Price> findPrices(Long brandId, Long productId, LocalDateTime applicationDate) {
        return jpaPriceRepository.findPrices(brandId, productId, applicationDate);
    }
}