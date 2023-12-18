package ru.mirea.orderservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import ru.mirea.orderservice.dto.InventoryResponse;
import ru.mirea.orderservice.dto.OrderDTO;
import ru.mirea.orderservice.dto.OrderLineItemsDTO;
import ru.mirea.orderservice.entity.Order;
import ru.mirea.orderservice.entity.OrderLineItems;
import ru.mirea.orderservice.repository.OrderRepository;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class OrderService {
    private final OrderRepository orderRepository;
    private final WebClient webClient;

    public void placeOrder(OrderDTO orderDTO) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItems> orderLineItems = orderDTO.orderLineItemsDTOS()
                .stream()
                .map(this::mapToDTO)
                .toList();

        order.setOrderLineItemsList(orderLineItems);

        List<String> skuCodes = order.getOrderLineItemsList().stream().map(OrderLineItems::getSkuCode)
                .toList();

        InventoryResponse[] inventoryResponsesArray =  webClient.get()
                .uri("http://localhost:8082/api/inventory",
                        uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build())
                        .retrieve()
                                .bodyToMono(InventoryResponse[].class)
                                        .block();

        boolean allProductsInStocks = Arrays.stream(inventoryResponsesArray)
                .allMatch(InventoryResponse::isInStock);

        if(Boolean.TRUE.equals(allProductsInStocks)) {
            orderRepository.save(order);
            log.info("New Order Saved");
        } else {
            throw new IllegalArgumentException("Product isn't in stock, please try again later");
        }
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
