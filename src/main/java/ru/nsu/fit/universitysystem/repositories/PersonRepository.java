package ru.nsu.fit.universitysystem.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.nsu.fit.universitysystem.entities.Person;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface PersonRepository extends CrudRepository<Person, Long> {
}
