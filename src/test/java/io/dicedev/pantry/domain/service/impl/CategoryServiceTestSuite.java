package io.dicedev.pantry.domain.service.impl;

import io.dicedev.pantry.command.entity.CategoryEntity;
import io.dicedev.pantry.command.repository.CategoryRepository;
import io.dicedev.pantry.domain.dto.CategoriesDto;
import io.dicedev.pantry.domain.dto.CategoryDto;
import io.dicedev.pantry.domain.service.CategoryService;
import io.dicedev.pantry.mapper.CategoryMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceTestSuite {

    CategoryService categoryService;

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private CategoryMapper categoryMapper;

    @BeforeEach
    private void setUp() {
        categoryService = new CategoryServiceImpl(categoryRepository, categoryMapper);
    }

    @Test
    public void shouldGetOneCategoryAfterAddingOneCategory() {
        //Given
        CategoryDto categoryDto = new CategoryDto();

        //When
        categoryService.addCategory(categoryDto);

        //Then
        Mockito.verify(categoryRepository, Mockito.times(1)).save(any());
    }

    @Test
    public void shouldGetAllCategories() {
        //Given
        UUID categoryId = UUID.randomUUID();
        String categoryName = "category";
        CategoryEntity categoryEntity = new CategoryEntity(categoryId, categoryName);
        CategoryDto categoryDto = new CategoryDto(categoryId, categoryName);
        List<CategoryEntity> categoryEntities = List.of(categoryEntity);
        Mockito.when(categoryMapper.categoryEntityToCategoryDto(categoryEntity)).thenReturn(categoryDto);
        Mockito.when(categoryRepository.findAll()).thenReturn(categoryEntities);

        //When
        CategoriesDto categoriesDto = categoryService.getCategories();

        //Then
        assertAll(
                () -> assertEquals(categoryName, categoriesDto.getCategoriesDto().get(0).getName()),
                () -> assertEquals(categoryId, categoriesDto.getCategoriesDto().get(0).getId())
        );
    }

    @Test
    public void shouldGetEmptyCategoryListWhenNoCategoryAdded() {
        //Given

        //When
        CategoriesDto result = categoryService.getCategories();

        //Then
        assertEquals(0, result.getCategoriesDto().size());
    }

    @Test
    public void shouldRenameExistingCategory() {
        //Given
        CategoryDto categoryDto = new CategoryDto();
        categoryService.addCategory(categoryDto);

        //When
        categoryService.renameCategory(categoryDto);

        //Then
        Mockito.verify(categoryRepository, Mockito.times(1)).findById(categoryDto.getId());
        Mockito.verify(categoryRepository, Mockito.times(1)).save(any());
    }

    @Test
    public void shouldNotRenameNotExistingCategory() {
        //Given
        CategoryDto categoryDto = new CategoryDto();

        //When
        categoryService.renameCategory(categoryDto);

        //Then
        Mockito.verify(categoryRepository, Mockito.times(1)).findById(categoryDto.getId());
        Mockito.verify(categoryRepository, Mockito.times(0)).save(any());
    }
}
