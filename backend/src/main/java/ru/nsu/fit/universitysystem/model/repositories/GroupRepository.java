package ru.nsu.fit.universitysystem.model.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.nsu.fit.universitysystem.model.entities.Group;

@Repository
public interface GroupRepository extends CrudRepository<Group, Long> {
}
