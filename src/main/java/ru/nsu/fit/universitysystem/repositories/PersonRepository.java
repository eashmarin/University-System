package ru.nsu.fit.universitysystem.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.nsu.fit.universitysystem.entities.Person;

public interface PersonRepository extends CrudRepository<Person, Long> {
}
