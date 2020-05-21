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

    @PutMapping("/{id}/{newName}")
    public void renameProduct(@PathVariable UUID id, @PathVariable String newName) {
        service.renameProduct(id, newName);
        log.info(id.toString() + ", " + newName);
    }

    @PutMapping("/{id}")
    public void renameProduct2(@PathVariable UUID id, @RequestBody ProductDto productDto) {
        service.renameProduct2(id, productDto);
        log.info(id.toString() + ", " + productDto.getName());
    }
}
