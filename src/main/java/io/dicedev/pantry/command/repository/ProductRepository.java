package io.dicedev.pantry.command.repository;

import io.dicedev.pantry.command.entity.ProductEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepository extends CrudRepository<ProductEntity, UUID> {
    ProductEntity findByName(String name);

    Optional<ProductEntity> findById(UUID productId);

    @Query
    List<ProductEntity> findByDeleted();
}
