package ru.mirea.inventoryservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mirea.inventoryservice.entity.Inventory;

import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    Optional<Inventory> findBySkuCode(String skuCode);
}
