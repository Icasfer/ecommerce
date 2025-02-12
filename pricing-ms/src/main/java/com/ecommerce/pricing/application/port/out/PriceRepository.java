package com.ecommerce.pricing.application.port.out;

import java.time.LocalDateTime;
import java.util.List;

import com.ecommerce.pricing.domain.model.Price;

public interface PriceRepository {
    List<Price> findPrices(Integer brandId, Long productId, LocalDateTime applicationDate);
}