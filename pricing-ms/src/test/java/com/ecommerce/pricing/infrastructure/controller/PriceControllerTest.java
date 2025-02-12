package com.ecommerce.pricing.infrastructure.controller;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import lombok.RequiredArgsConstructor;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@RequiredArgsConstructor
class PriceControllerTest {

    private final MockMvc mockMvc;

    @Test
    void testGetProductPrice_Success() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/prices?brandId=1&productId=35455&applicationDate=2020-06-14T10:00:00")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void testGetProductPrice_NotFound() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/prices?brandId=99&productId=99999&applicationDate=2020-06-14T10:00:00")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void testGetProductPrice_InvalidDateFormat() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/prices?brandId=1&productId=35455&applicationDate=invalid-date")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}
