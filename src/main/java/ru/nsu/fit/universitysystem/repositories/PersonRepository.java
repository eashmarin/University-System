package ru.nsu.fit.universitysystem.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.nsu.fit.universitysystem.entities.Person;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {
}
