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

import java.util.*;

@Service
@AllArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private ProductMapper productMapper;
    private final ProductRepository productRepository;
    private final List<ProductValidator> productValidator;

    @Override
    public ProductsDto getProducts() {
        log.info("Getting all products");
        ProductsDto productsDto = new ProductsDto();
        productsDto.setProductsDto(new ArrayList<>());
        productRepository.findByDeleted()
                .stream()
                .map(product -> productMapper.productEntityToProductDto(product))
                .forEach(productDto -> productsDto.getProductsDto().add(productDto));
        log.info("Found {} product(s)", productsDto.getProductsDto().size());
        return productsDto;
    }

    @Override
    public void addProduct(ProductDto productDto) {
        log.info("Adding product {}", productDto);
        productValidator.forEach(it -> it.isValid(productDto));
        Integer productAmount = productDto.getAmount();
        ProductEntity product = checkIfNameAlreadyExists(productDto);
        if (Objects.isNull(product)) {
            product = productMapper.productDtoToProductEntity(productDto);
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

    private ProductEntity checkIfNameAlreadyExists(ProductDto productDto) {
        String productName = productDto.getName().toLowerCase();
        String name = productName.substring(0, 1).toUpperCase() + productName.substring(1);
        ProductEntity product = productRepository.findByName(name);
        return product;
    }

}
