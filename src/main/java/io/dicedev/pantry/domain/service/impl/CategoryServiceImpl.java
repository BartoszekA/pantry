package io.dicedev.pantry.domain.service.impl;

import io.dicedev.pantry.command.entity.CategoryEntity;
import io.dicedev.pantry.command.repository.CategoryRepository;
import io.dicedev.pantry.domain.dto.CategoriesDto;
import io.dicedev.pantry.domain.dto.CategoryDto;
import io.dicedev.pantry.domain.service.CategoryService;
import io.dicedev.pantry.mapper.CategoryMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    private CategoryMapper categoryMapper;

    @Override
    public CategoriesDto getCategories() {
        log.info("Getting all categories");
        CategoriesDto categoriesDto = new CategoriesDto();
        categoriesDto.setCategoriesDto(new ArrayList<>());
        categoryRepository.findAll()
                .forEach(categoryEntity -> categoriesDto.getCategoriesDto()
                        .add(categoryMapper.categoryEntityToCategoryDto(categoryEntity)));
        log.info("Found following categories: " + categoriesDto.toString());
        return categoriesDto;
    }

    @Override
    public void addCategory(CategoryDto categoryDto) {
        log.info("Adding category {}" + categoryDto);
        UUID categoryId = categoryDto.getId();
        CategoryEntity categoryEntity = categoryMapper.categoryDtoToCategoryEntity(categoryDto);
        categoryRepository.save(categoryEntity);
        log.info("Category {} added" + categoryId);
    }

    @Override
    public void renameCategory(CategoryDto categoryDto) {

    }
}
