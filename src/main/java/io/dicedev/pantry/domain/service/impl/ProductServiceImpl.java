package io.dicedev.pantry.domain.service.impl;

import io.dicedev.pantry.command.entity.ProductEntity;
import io.dicedev.pantry.command.repository.ProductRepository;
import io.dicedev.pantry.domain.dto.ProductDto;
import io.dicedev.pantry.domain.dto.ProductsDto;
import io.dicedev.pantry.domain.service.ProductService;
import io.dicedev.pantry.domain.validate.ProductValidator;
import io.dicedev.pantry.mapper.ProductMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.BiFunction;

@Service
@AllArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductMapper productMapper;
    private final ProductRepository productRepository;
    private final List<ProductValidator> productValidator;

    @Override
    public ProductsDto getProducts() {
        log.info("Getting all products");
        ProductsDto productsDto = new ProductsDto();
        productsDto.setProductsDto(new ArrayList<>());
        productRepository.findByDeleted()
                .stream()
                .map(productMapper::productEntityToProductDto)
                .forEach(productsDto.getProductsDto()::add);
        log.info("Found {} product(s)", productsDto.getProductsDto().size());
        return productsDto;
    }

    @Override
    public void addProduct(ProductDto productDto) {
        log.info("Adding product {}", productDto);
        productValidator.forEach(it -> it.isValid(productDto));
        var name = formattedName(productDto);
        ProductEntity productEntity = productRepository.findByName(name)
                .map(entity -> calculateAmount(entity, productDto, (e, d) -> Integer.sum(e.getAmount(),
                        d.getAmount())))
                .orElse(productMapper.productDtoToProductEntity(productDto));
        productRepository.save(productEntity);
        log.info("Product {} added", productDto.getId());
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

    @Override
    public void removeProduct(ProductDto productDto) {
        log.info("Removing product {}", productDto);
        UUID productId = productDto.getId();
        Optional<ProductEntity> product = productRepository.findById(productId);
        if (product.isPresent()) {
            ProductEntity productEntity = product.get();
            productEntity.setDeleted(true);
            productRepository.save(productEntity);
            log.info("Product {} deleted", productDto);
        }
    }

    private String formattedName(ProductDto productDto) {
        String productName = productDto.getName().toLowerCase();
        return productName.substring(0, 1).toUpperCase() + productName.substring(1);
    }

    private ProductEntity calculateAmount(ProductEntity productEntity, ProductDto productDto,
                                          BiFunction<ProductEntity, ProductDto, Integer> biFunction) {
        Integer result = biFunction.apply(productEntity, productDto);
        productEntity.setAmount(result);
        return productEntity;
    }
}
