package ru.mirea.orderservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.mirea.orderservice.dto.OrderDTO;
import ru.mirea.orderservice.dto.OrderLineItemsDTO;
import ru.mirea.orderservice.entity.Order;
import ru.mirea.orderservice.entity.OrderLineItems;
import ru.mirea.orderservice.repository.OrderRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {
    private final OrderRepository orderRepository;

    public void placeOrder(OrderDTO orderDTO) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        order.setOrderLineItemsList(
                orderDTO.orderLineItemsDTOS()
                        .stream()
                        .map(this::mapToDTO)
                        .toList());

        orderRepository.save(order);
        log.info("New Order Saved");
    }

    private OrderLineItems mapToDTO(OrderLineItemsDTO orderLineItemsDTO) {
        return new OrderLineItems(
                orderLineItemsDTO.Id(),
                orderLineItemsDTO.skuCode(),
                orderLineItemsDTO.price(),
                orderLineItemsDTO.quantity()
        );
    }
}
