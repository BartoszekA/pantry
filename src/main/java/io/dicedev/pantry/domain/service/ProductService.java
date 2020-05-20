package io.dicedev.pantry.domain.service;

import io.dicedev.pantry.domain.dto.ProductDto;
import io.dicedev.pantry.domain.dto.ProductsDto;

import java.util.UUID;

public interface ProductService {
    ProductsDto getProducts();
    void addProduct(ProductDto productDto);
    void renameProduct(UUID id, String newName);
    void renameProduct2(UUID id, ProductDto productDto);
}
