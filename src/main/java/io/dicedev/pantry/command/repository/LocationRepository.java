package io.dicedev.pantry.command.repository;

import io.dicedev.pantry.command.entity.LocationEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LocationRepository extends CrudRepository<LocationEntity, UUID> {

}
