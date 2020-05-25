package io.dicedev.pantry.domain.service;

import io.dicedev.pantry.domain.dto.ProductDto;
import io.dicedev.pantry.domain.dto.ProductsDto;

public interface ProductService {
    ProductsDto getProducts();
    void addProduct(ProductDto productDto);
    void renameProduct(ProductDto productDto);
}
