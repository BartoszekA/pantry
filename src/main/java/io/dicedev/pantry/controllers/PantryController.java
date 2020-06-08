package io.dicedev.pantry.controllers;

import io.dicedev.pantry.domain.dto.ProductDto;
import io.dicedev.pantry.domain.dto.ProductsDto;
import io.dicedev.pantry.domain.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
@CrossOrigin
@AllArgsConstructor
@Slf4j
public class PantryController {

    private ProductService service;


    @GetMapping
    public ProductsDto getProducts() {
        return service.getProducts();
    }

    @PostMapping
    public void addProduct(@RequestBody ProductDto product) {
        service.addProduct(product);
        log.info("Product = {}", product);
    }

    @PutMapping
    public void renameProduct(@RequestBody ProductDto productDto) {
        service.renameProduct(productDto);
    }
}
