package io.dicedev.pantry.controllers;

import io.dicedev.pantry.domain.dto.ProductDto;
import io.dicedev.pantry.domain.dto.ProductsDto;
import io.dicedev.pantry.domain.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
@CrossOrigin
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ProductsDto getProducts() {
        return productService.getProducts();
    }

    @PostMapping
    public void addProduct(@RequestBody ProductDto product) {
        productService.addProduct(product);
    }

    @PutMapping
    public void renameProduct(@RequestBody ProductDto productDto) {
        productService.renameProduct(productDto);
    }

    @DeleteMapping
    public void removeProduct(@RequestBody ProductDto productDto) {

        productService.removeProduct(productDto);
    }
}
