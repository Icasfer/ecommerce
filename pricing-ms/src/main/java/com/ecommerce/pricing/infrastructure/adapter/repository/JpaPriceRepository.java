package com.ecommerce.pricing.infrastructure.adapter.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ecommerce.pricing.domain.model.Price;

@Repository
public interface JpaPriceRepository extends JpaRepository<Price, Long> {
    @Query("SELECT p FROM Price p WHERE p.brandId = :brandId AND p.productId = :productId AND :applicationDate BETWEEN p.startDate AND p.endDate")
    List<Price> findPrices(Long brandId, Long productId, LocalDateTime applicationDate);
}
