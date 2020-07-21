package io.dicedev.pantry.domain.dto;


import lombok.*;

import java.util.UUID;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlaceDto {
    private UUID id;
    private String name;
}
