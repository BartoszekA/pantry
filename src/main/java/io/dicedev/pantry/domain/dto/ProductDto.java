package io.dicedev.pantry.domain.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
public class ProductDto {
    private UUID id;
    private String name;
    private Integer amount;
}
