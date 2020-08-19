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

import java.util.*;

@Service
@AllArgsConstructor
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    private final CategoryMapper categoryMapper;

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
        log.info("Adding category {}", categoryDto);
        UUID categoryId = categoryDto.getId();
        CategoryEntity categoryEntity = categoryMapper.categoryDtoToCategoryEntity(categoryDto);
        categoryRepository.save(categoryEntity);
        log.info("Category {} added", categoryId);
    }

    @Override
    public void renameCategory(CategoryDto categoryDto) {
        log.info("Renaming category {}", categoryDto);
        UUID categoryId = categoryDto.getId();
        Optional<CategoryEntity> category = categoryRepository.findById(categoryId);
        if (category.isPresent()) {
            CategoryEntity categoryEntity = category.get();
            categoryEntity.setName(categoryDto.getName());
            categoryRepository.save(categoryEntity);
            log.info("Category {} renamed", categoryDto);
        }
    }
}
