package ru.mirea.orderservice.dto;

import ru.mirea.orderservice.entity.OrderLineItems;

import java.util.List;

public record OrderDTO (List<OrderLineItemsDTO> orderLineItemsDTOS) {}
