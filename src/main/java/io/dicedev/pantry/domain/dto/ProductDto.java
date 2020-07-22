package io.dicedev.pantry.domain.dto;

import io.dicedev.pantry.command.entity.CategoryEntity;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDto {
    private UUID id;
    private String name;
    private Integer amount;
    private CategoryEntity category;
}
