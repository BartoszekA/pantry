package io.dicedev.pantry.domain.service;

import io.dicedev.pantry.domain.dto.ProductDto;
import io.dicedev.pantry.domain.dto.ProductsDto;

import java.security.InvalidKeyException;

public interface ProductService {
    ProductsDto getProducts();
    void addProduct(ProductDto productDto) throws InvalidKeyException;
    void renameProduct(ProductDto productDto);
}
