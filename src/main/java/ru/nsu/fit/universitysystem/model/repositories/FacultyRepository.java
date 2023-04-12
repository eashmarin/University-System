package ru.nsu.fit.universitysystem.model.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.nsu.fit.universitysystem.model.entities.Faculty;

@Repository
public interface FacultyRepository extends CrudRepository<Faculty, Long> {
}
