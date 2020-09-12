package io.dicedev.pantry.domain.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.GregorianCalendar;
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
    private CategoryDto category;
    private Boolean deleted;
    private LocalDate expirationDate;
}
