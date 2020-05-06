package io.dicedev.pantry.domain.service.impl;

import io.dicedev.pantry.domain.dto.ProductDto;
import io.dicedev.pantry.domain.dto.ProductsDto;
import io.dicedev.pantry.domain.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductsDto productsDto;

    @Override
    public ProductsDto getProducts() {
        return productsDto;
    }

    @Override
    public void addProduct(ProductDto productDto) {
        if (productsDto == null) {
            productsDto = new ProductsDto();
            productsDto.setProductsDto(new ArrayList<>());
        }
        productsDto.getProductsDto().add(productDto);
    }
}
