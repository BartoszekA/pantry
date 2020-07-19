package io.dicedev.pantry.domain.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
public class CategoriesDto {
    private List<CategoryDto> categoriesDto;

    @Override
    public String toString() {
        return "CategoriesDto{" +
                "categories=" + categoriesDto +
                "}";
    }
}
