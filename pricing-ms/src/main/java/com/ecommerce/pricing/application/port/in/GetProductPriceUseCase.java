package com.ecommerce.pricing.application.port.in;

import java.time.LocalDateTime;
import java.util.Optional;

import com.ecommerce.pricing.application.dto.PricesResponseDTO;

public interface GetProductPriceUseCase {
    Optional<PricesResponseDTO> getProductPrice(Integer brandId, Long productId, LocalDateTime applicationDate);
}
