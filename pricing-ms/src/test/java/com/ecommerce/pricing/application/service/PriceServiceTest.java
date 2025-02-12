package com.ecommerce.pricing.application.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ecommerce.pricing.application.dto.PricesResponseDTO;
import com.ecommerce.pricing.application.port.out.PriceRepository;
import com.ecommerce.pricing.domain.model.Price;
import com.ecommerce.pricing.infrastructure.exception.PriceNotFoundException;

@ExtendWith(MockitoExtension.class)
class PriceServiceTest {

    @Mock
    private PriceRepository priceRepository;

    @InjectMocks
    private PriceService priceService;

    private List<Price> testPrices;

    @BeforeEach
    void setUp() {
        testPrices = List.of(
                Price.builder()
                        .brandId(1)
                        .productId(35455L)
                        .priceList(1)
                        .startDate(LocalDateTime.of(2020, 6, 14, 0, 0))
                        .endDate(LocalDateTime.of(2020, 12, 31, 23, 59))
                        .priority(0)
                        .finalPrice(35.50)
                        .currency("EUR")
                        .build(),
                Price.builder()
                        .brandId(1)
                        .productId(35455L)
                        .priceList(2)
                        .startDate(LocalDateTime.of(2020, 6, 14, 15, 0))
                        .endDate(LocalDateTime.of(2020, 6, 14, 18, 30))
                        .priority(1)
                        .finalPrice(25.45)
                        .currency("EUR")
                        .build(),
                Price.builder()
                        .brandId(1)
                        .productId(35455L)
                        .priceList(3)
                        .startDate(LocalDateTime.of(2020, 6, 15, 0, 0))
                        .endDate(LocalDateTime.of(2020, 6, 15, 11, 0))
                        .priority(1)
                        .finalPrice(30.50)
                        .currency("EUR")
                        .build(),
                Price.builder()
                        .brandId(1)
                        .productId(35455L)
                        .priceList(4)
                        .startDate(LocalDateTime.of(2020, 6, 15, 16, 0))
                        .endDate(LocalDateTime.of(2020, 12, 31, 23, 59))
                        .priority(1)
                        .finalPrice(38.95)
                        .currency("EUR")
                        .build()
        );
    }

    @Test
    void getProductPrice_shouldReturnPrice_whenValidRequest() {
        when(priceRepository.findPrices(anyInt(), anyLong(), any(LocalDateTime.class)))
                .thenReturn(List.of(testPrices.get(0)));

        Optional<PricesResponseDTO> result = priceService.getProductPrice(1, 35455L,
                LocalDateTime.of(2020, 6, 14, 10, 0));

        assertTrue(result.isPresent());
        assertEquals(35.50, result.get().getFinalPrice());
    }

    @Test
    void getProductPrice_shouldThrowException_whenPriceNotFound() {
        when(priceRepository.findPrices(anyInt(), anyLong(), any(LocalDateTime.class)))
                .thenReturn(List.of());

        assertThrows(PriceNotFoundException.class,
                () -> priceService.getProductPrice(1, 35455L, LocalDateTime.of(2020, 6, 14, 10, 0)));
    }

    @Test
    void test1() {
        when(priceRepository.findPrices(1, 35455L, LocalDateTime.of(2020, 6, 14, 10, 0)))
                .thenReturn(List.of(testPrices.get(0)));
        assertEquals(35.50, priceService.getProductPrice(1, 35455L, LocalDateTime.of(2020, 6, 14, 10, 0)).get().getFinalPrice());
    }

    @Test
    void test2() {
        when(priceRepository.findPrices(1, 35455L, LocalDateTime.of(2020, 6, 14, 16, 0)))
                .thenReturn(List.of(testPrices.get(1)));
        assertEquals(25.45, priceService.getProductPrice(1, 35455L, LocalDateTime.of(2020, 6, 14, 16, 0)).get().getFinalPrice());
    }

    @Test
    void test3() {
        when(priceRepository.findPrices(1, 35455L, LocalDateTime.of(2020, 6, 14, 21, 0)))
                .thenReturn(List.of(testPrices.get(0)));
        assertEquals(35.50, priceService.getProductPrice(1, 35455L, LocalDateTime.of(2020, 6, 14, 21, 0)).get().getFinalPrice());
    }

    @Test
    void test4() {
        when(priceRepository.findPrices(1, 35455L, LocalDateTime.of(2020, 6, 15, 10, 0)))
                .thenReturn(List.of(testPrices.get(2)));
        assertEquals(30.50, priceService.getProductPrice(1, 35455L, LocalDateTime.of(2020, 6, 15, 10, 0)).get().getFinalPrice());
    }

    @Test
    void test5() {
        when(priceRepository.findPrices(1, 35455L, LocalDateTime.of(2020, 6, 16, 21, 0)))
                .thenReturn(List.of(testPrices.get(3)));
        assertEquals(38.95, priceService.getProductPrice(1, 35455L, LocalDateTime.of(2020, 6, 16, 21, 0)).get().getFinalPrice());
    }
}