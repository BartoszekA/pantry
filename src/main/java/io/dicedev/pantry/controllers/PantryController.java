package io.dicedev.pantry.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PantryController {

    private String product;

    @GetMapping("/product")
    public String getProduct() {
        return product;
    }

    @PostMapping("/product")
    public void addProduct(@RequestBody String product) {
        this.product = product;
        System.out.println(product);
    }
}
