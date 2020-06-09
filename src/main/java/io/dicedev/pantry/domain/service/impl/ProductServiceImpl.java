package io.dicedev.pantry.domain.service.impl;

import io.dicedev.pantry.command.entity.ProductEntity;
import io.dicedev.pantry.command.repository.ProductRepository;
import io.dicedev.pantry.domain.dto.ProductDto;
import io.dicedev.pantry.domain.dto.ProductsDto;
import io.dicedev.pantry.domain.service.ProductService;
import io.dicedev.pantry.domain.validate.ProductValidator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final List<ProductValidator> productValidator;

    @Override
    public ProductsDto getProducts() {
        log.info("Getting all products");
        ProductsDto productsDto = new ProductsDto();
        productsDto.setProductsDto(new ArrayList<>());
        productRepository.findAll()
                .forEach(product -> {
                    ProductDto productDto = ProductDto.builder()
                            .id(product.getId())
                            .name(product.getName())
                            .amount(product.getAmount())
                            .build();
                    productsDto.getProductsDto().add(productDto);
                });
        log.info("Found {} products", productsDto.getProductsDto().size());
        return productsDto;
    }

    @Override
    public void addProduct(ProductDto productDto) {
        log.info("Adding product {}", productDto);
        productValidator.forEach(it -> it.isValid(productDto));
        String productName = productDto.getName();
        Integer productAmount = productDto.getAmount();
        ProductEntity product = productRepository.findByName(productName);
        if (Objects.isNull(product)) {
            product = ProductEntity.builder()
                    .name(productDto.getName())
                    .amount(productAmount)
                    .build();
        } else {
            Integer newProductAmount = product.getAmount() + productAmount;
            product.setAmount(newProductAmount);
        }
        var productId = productRepository.save(product);
        log.info("Product {} added", productId);
    }

    @Override
    public void renameProduct(ProductDto productDto) {
        log.info("Renaming product {}", productDto);
        UUID productId = productDto.getId();
        Optional<ProductEntity> product = productRepository.findById(productId);
        if (product.isPresent()) {
            ProductEntity productEntity = product.get();
            productEntity.setName(productDto.getName());
            productRepository.save(productEntity);
            log.info("Product {} renamed", productDto);
        }
    }
}
