package io.dicedev.pantry.domain.service;

import io.dicedev.pantry.domain.dto.CategoriesDto;
import io.dicedev.pantry.domain.dto.CategoryDto;

public interface CategoryService {

    CategoriesDto getCategories();

    void addCategory(CategoryDto categoryDto);

    void renameCategory(CategoryDto categoryDto);
}
