package io.dicedev.pantry.domain.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.util.UUID;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@Getter
public enum ProductCategoryEnum {
    DAIRY("Dairy", "4e457900-b16e-11ea-b3de-0242ac130004"),
    FRUITS_AND_VEGETABLES("Fruits and vegetables", "4e457b62-b16e-11ea-b3de-0242ac130004"),
    LOOSE_PRODUCTS("Loose products", "4e457c66-b16e-11ea-b3de-0242ac130004"),
    BREAD("Bread", "4e457d4c-b16e-11ea-b3de-0242ac130004"),
    SWEETS("Sweets", "4e457fb8-b16e-11ea-b3de-0242ac130004"),
    COFFEE_AND_TEA("Coffee and tea", "4e45809e-b16e-11ea-b3de-0242ac130004"),
    CANNED_FOOD("Canned food", "4e458166-b16e-11ea-b3de-0242ac130004");

    final private String name;
    final private UUID id;

    ProductCategoryEnum(String name, String id) {
        this.name = name;
        this.id = UUID.fromString(id);
    }
}
