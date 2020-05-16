package io.dicedev.pantry.domain.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class ProductDto {
    private UUID id;
    private String name;
    private Integer amount;

    public ProductDto(String name) {
        this.name = name;
    }
}
