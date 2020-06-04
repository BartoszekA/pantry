package io.dicedev.pantry.domain.service.impl;

import io.dicedev.pantry.command.entity.ProductEntity;
import io.dicedev.pantry.command.repository.ProductRepository;
import io.dicedev.pantry.domain.dto.ProductDto;
import io.dicedev.pantry.domain.dto.ProductsDto;
import io.dicedev.pantry.domain.service.ProductService;
import io.dicedev.pantry.domain.validate.ProductValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final List<ProductValidator> productValidator;

    @Override
    public ProductsDto getProducts() {
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
        return productsDto;
    }

    @Override
    public void addProduct(ProductDto productDto) {
        productValidator.forEach(it -> it.isValid(productDto));
        String productName = productDto.getName();
        ProductEntity product = productRepository.findByName(productName);
        if (Objects.isNull(product)) {
            product = ProductEntity.builder()
                    .name(productDto.getName())
                    .amount(1)
                    .build();
        } else {
            Integer newProductAmount = product.getAmount() + 1;
            product.setAmount(newProductAmount);
        }
        productRepository.save(product);
    }

    @Override
    public void renameProduct(ProductDto productDto) {
        UUID productId = productDto.getId();
        Optional<ProductEntity> product = productRepository.findById(productId);
        if (product.isPresent()) {
            ProductEntity productEntity = product.get();
            productEntity.setName(productDto.getName());
            productRepository.save(productEntity);
        }
    }
}
