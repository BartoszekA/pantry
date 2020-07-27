package io.dicedev.pantry.command.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "PRODUCTS")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private UUID id;
    private String name;
    private Integer amount;
    @ManyToOne(cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    @JoinColumn(name = "CATEGORY",
            referencedColumnName = "ID",
            foreignKey = @ForeignKey(name = "FK_CATEGORY_TO_PRODUCT"))
    private CategoryEntity category;
    private Boolean deleted;
}
