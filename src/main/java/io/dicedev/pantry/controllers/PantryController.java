package io.dicedev.pantry.controllers;

import io.dicedev.pantry.domain.dto.ProductDto;
import io.dicedev.pantry.domain.dto.ProductsDto;
import io.dicedev.pantry.domain.enums.ProductCategoryEnum;
import io.dicedev.pantry.domain.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/products")
@CrossOrigin
@AllArgsConstructor
public class PantryController {

    private ProductService service;

    @GetMapping
    public ProductsDto getProducts() {
        return service.getProducts();
    }

    @GetMapping("/categories")
    public List<ProductCategoryEnum> getProductsCategories() {
        return Arrays.asList(ProductCategoryEnum.values());
    }

    @PostMapping
    public void addProduct(@RequestBody ProductDto product) {
        service.addProduct(product);
    }

    @PutMapping
    public void renameProduct(@RequestBody ProductDto productDto) {
        service.renameProduct(productDto);
    }

    @DeleteMapping
    public void removeProduct(@RequestBody ProductDto productDto) {
        service.removeProduct(productDto);
    }
}
