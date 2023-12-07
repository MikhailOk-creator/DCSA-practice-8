package ru.mirea.dcsa_8.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.mirea.dcsa_8.dto.ProductDTO;
import ru.mirea.dcsa_8.entity.ProductEntity;
import ru.mirea.dcsa_8.mapper.ProductMapper;
import ru.mirea.dcsa_8.repository.ProductRepository;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

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
