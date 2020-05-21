package io.dicedev.pantry.controllers;

import io.dicedev.pantry.domain.dto.ProductDto;
import io.dicedev.pantry.domain.dto.ProductsDto;
import io.dicedev.pantry.domain.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequestMapping("/products")
@CrossOrigin
@AllArgsConstructor
@Log
public class PantryController {

    private ProductService service;


    @GetMapping
    public ProductsDto getProducts() {
        return service.getProducts();
    }

    @PostMapping
    public void addProduct(@RequestBody ProductDto product) {
        service.addProduct(product);
        log.info(product.toString());
    }

    @PutMapping
    public void renameProduct(@RequestBody ProductDto productDto) {
        service.renameProduct(productDto.getId(), productDto);
    }
}
