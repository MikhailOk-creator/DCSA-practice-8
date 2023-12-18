package ru.mirea.productservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.mirea.productservice.entity.ProductEntity;

public interface ProductRepository extends MongoRepository<ProductEntity, String> {
}
