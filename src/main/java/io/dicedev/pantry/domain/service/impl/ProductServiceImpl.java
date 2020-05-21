package io.dicedev.pantry.domain.service.impl;

import io.dicedev.pantry.command.entity.ProductEntity;
import io.dicedev.pantry.command.repository.ProductRepository;
import io.dicedev.pantry.domain.dto.ProductDto;
import io.dicedev.pantry.domain.dto.ProductsDto;
import io.dicedev.pantry.domain.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

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
        productRepository.save(ProductEntity.builder()
                .name(productDto.getName())
                .amount(1)
                .build());
    }

    @Override
    public void renameProduct(UUID id, ProductDto productDto) {
/*        String newName = productDto.getName();
        ProductDto product = productsDto.getProductsDto().stream()
                .filter(prod -> id.equals(prod.getId()))
                .findFirst().orElseThrow();
        product.setName(newName);*/
    }
}
