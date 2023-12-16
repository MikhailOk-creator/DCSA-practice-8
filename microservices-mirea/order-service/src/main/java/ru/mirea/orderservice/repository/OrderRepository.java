package ru.mirea.orderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mirea.orderservice.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
