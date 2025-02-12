package com.ecommerce.pricing.domain.repository;

import java.time.LocalDateTime;
import java.util.List;

import com.ecommerce.pricing.domain.model.Price;

public interface PriceRepository {
    List<Price> findPrices(Integer brandId, Long productId, LocalDateTime applicationDate);
}