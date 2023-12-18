package ru.mirea.productservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.mirea.productservice.dto.ProductDTO;
import ru.mirea.productservice.entity.ProductEntity;
import ru.mirea.productservice.repository.ProductRepository;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public void createProduct(ProductDTO newProduct) {
        ProductEntity product = ProductEntity.builder()
                .name(newProduct.getName())
                .description(newProduct.getDescription())
                .price(newProduct.getPrice())
                .build();

        productRepository.save(product);
        log.info("Product {} saved", product.getId());
    }

    public List<ProductEntity> getAllProducts () {
        return productRepository.findAll();
    }
}
