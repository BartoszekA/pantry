package io.dicedev.pantry.controllers;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PantryController {

    private List<String> products = new ArrayList<>();

    @GetMapping("/product/{id}")
    public String getProduct(@PathVariable int id) {
        return products.get(id+1);
    }

    @GetMapping("/products")
    public String getProducts() {
        return products.toString();
    }

    @PostMapping("/product")
    public void addProduct(@RequestBody String product) {
        products.add(product);
        System.out.println(product);
    }
}
