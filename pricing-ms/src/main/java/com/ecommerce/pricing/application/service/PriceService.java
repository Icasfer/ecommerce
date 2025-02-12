package com.ecommerce.pricing.application.service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ecommerce.pricing.application.dto.PricesResponseDTO;
import com.ecommerce.pricing.application.port.in.GetProductPriceUseCase;
import com.ecommerce.pricing.domain.model.Price;
import com.ecommerce.pricing.domain.repository.PriceRepository;
import com.ecommerce.pricing.infrastructure.exception.PriceNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PriceService implements GetProductPriceUseCase {
    private final PriceRepository priceRepository;

    @Override
    public Optional<PricesResponseDTO> getProductPrice(Integer brandId, Long productId, LocalDateTime applicationDate) {
        return priceRepository.findPrices(brandId, productId, applicationDate).stream()
                .max(Comparator.comparingInt(Price::getPriority))
                .map(price -> PricesResponseDTO.builder()
                        .productId(price.getProductId()).brandId(price.getBrandId()).priceList(price.getPriceList())
                        .startDate(price.getStartDate()).endDate(price.getEndDate()).finalPrice(price.getFinalPrice())
                        .currency(price.getCurrency())
                        .build())
                .or(() -> {
                    throw new PriceNotFoundException("No product price found for given criteria");
                });
    }
}