package ru.nsu.fit.universitysystem.model.repositories.custom;

import ru.nsu.fit.universitysystem.model.entities.Person;
import ru.nsu.fit.universitysystem.model.enums.Gender;

import java.util.List;

@CustomRepository
public interface PersonRepository {
    Person findByLogin(String login);
    List<Person> findByGender(Gender gender);
}
