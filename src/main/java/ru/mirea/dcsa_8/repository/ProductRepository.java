package ru.mirea.dcsa_8.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.mirea.dcsa_8.entity.ProductEntity;

public interface ProductRepository extends MongoRepository<ProductEntity, String> {
}
