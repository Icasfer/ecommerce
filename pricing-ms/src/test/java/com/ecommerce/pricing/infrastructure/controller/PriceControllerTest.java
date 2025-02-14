package com.ecommerce.pricing.infrastructure.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class PriceControllerTest {

    @Autowired
    private MockMvc mockMvc;

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
    void testCase1() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/prices?brandId=1&productId=35455&applicationDate=2020-06-14T10:00:00"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.finalPrice", equalTo(35.50)));
    }

    @Test
    void testCase2() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/prices?brandId=1&productId=35455&applicationDate=2020-06-14T16:00:00"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.finalPrice", equalTo(25.45)));
    }

    @Test
    void testCase3() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/prices?brandId=1&productId=35455&applicationDate=2020-06-14T21:00:00"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.finalPrice", equalTo(35.50)));
    }

    @Test
    void testCase4() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/prices?brandId=1&productId=35455&applicationDate=2020-06-15T10:00:00"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.finalPrice", equalTo(30.50)));
    }

    @Test
    void testCase5() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/prices?brandId=1&productId=35455&applicationDate=2020-06-16T21:00:00"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.finalPrice", equalTo(38.95)));
    }
}