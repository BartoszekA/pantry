package io.dicedev.pantry.controllers;

import io.dicedev.pantry.domain.dto.CategoriesDto;
import io.dicedev.pantry.domain.dto.CategoryDto;
import io.dicedev.pantry.domain.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
@CrossOrigin
@AllArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public CategoriesDto getProductsCategories() {
        return categoryService.getCategories();
    }

    @PostMapping
    public void addCategory(@RequestBody CategoryDto categoryDto) {
        categoryService.addCategory(categoryDto);
    }

    @PutMapping
    public void renameCategory(@RequestBody CategoryDto categoryDto) {
        categoryService.renameCategory(categoryDto);
    }
}
