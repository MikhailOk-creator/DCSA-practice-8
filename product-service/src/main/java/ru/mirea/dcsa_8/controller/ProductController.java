package ru.mirea.dcsa_8.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.mirea.dcsa_8.dto.ProductDTO;
import ru.mirea.dcsa_8.entity.ProductEntity;
import ru.mirea.dcsa_8.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct (@RequestBody ProductDTO newProduct) {
        productService.createProduct(newProduct);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductEntity> getAllProducts () {
        return productService.getAllProducts();
    }
}
