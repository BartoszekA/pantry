package io.dicedev.pantry.domain.service.impl;

import io.dicedev.pantry.domain.dto.ProductDto;
import io.dicedev.pantry.domain.dto.ProductsDto;
import io.dicedev.pantry.domain.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;

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
        String productName = productDto.getName();
        Optional<ProductDto> product = productsDto.getProductsDto()
                .stream()
                .filter(p -> p.getName().equals(productName))
                .findFirst();
        if (product.isPresent()) {
            ProductDto prod = product.get();
            prod.setAmount(increaseAmount(prod));
        } else {
            productDto.setAmount(1);
            productDto.setId(UUID.randomUUID());
            productsDto.getProductsDto().add(productDto);
        }
    }

    private Integer increaseAmount(ProductDto productDto) {
        Integer amount = productDto.getAmount();
        return ++amount;
    }
}
