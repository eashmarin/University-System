package ru.nsu.fit.universitysystem.model.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.nsu.fit.universitysystem.model.entities.Person;

import java.util.Date;
import java.util.Optional;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {
    Optional<Person> findByLogin(String login);

    Optional<Person> findByLoginOrBirthDateBetween(String login, Date birthDate, Date birthDate2);
}
