package ru.nsu.fit.universitysystem.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.nsu.fit.universitysystem.entities.Faculty;
import ru.nsu.fit.universitysystem.entities.Group;

@Repository
public interface FacultyRepository extends CrudRepository<Faculty, Long> {
}
