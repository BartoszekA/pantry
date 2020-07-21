package io.dicedev.pantry.command.repository;

import io.dicedev.pantry.command.entity.CategoryEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CategoryRepository extends CrudRepository<CategoryEntity, UUID> {
}
