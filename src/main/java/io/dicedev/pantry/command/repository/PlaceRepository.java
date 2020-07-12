package io.dicedev.pantry.command.repository;

import io.dicedev.pantry.command.entity.PlaceEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PlaceRepository extends CrudRepository<PlaceEntity, UUID> {

}
