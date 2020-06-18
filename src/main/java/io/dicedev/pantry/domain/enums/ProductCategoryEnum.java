package io.dicedev.pantry.domain.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@Getter
public enum ProductCategoryEnum {
    DAIRY("Dairy", 1),
    FRUITS_AND_VEGETABLES("Fruits and vegetables", 2),
    LOOSE_PRODUCTS("Loose products", 3),
    BREAD("Bread", 4),
    SWEETS("Sweets", 5),
    COFFEE_AND_TEA("Coffee and tea", 6),
    CANNED_FOOD("Canned food", 7);

    final private String name;
    final private Integer id;

    ProductCategoryEnum(String name, Integer id) {
        this.name = name;
        this.id = id;
    }
}
