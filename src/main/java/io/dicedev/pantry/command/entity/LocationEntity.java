package io.dicedev.pantry.command.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name="LOCATION")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class LocationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private UUID id;
    private String name;
}
