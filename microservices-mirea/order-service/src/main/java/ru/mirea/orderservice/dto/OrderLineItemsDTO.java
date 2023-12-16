package ru.mirea.orderservice.dto;

import java.math.BigDecimal;

public record OrderLineItemsDTO (
        Long Id,
        String skuCode,
        BigDecimal price,
        Integer quantity) {}
