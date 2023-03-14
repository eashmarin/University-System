package ru.nsu.fit.universitysystem.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.nsu.fit.universitysystem.entities.Group;
import ru.nsu.fit.universitysystem.entities.Student;

@Repository
public interface GroupRepository extends CrudRepository<Group, Long> {
}
