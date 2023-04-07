package ru.nsu.fit.universitysystem.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.nsu.fit.universitysystem.entities.Person;
import ru.nsu.fit.universitysystem.repositories.PersonRepository;

import java.util.Optional;

@Service
public class PersonService {
    private final PersonRepository repository;

    @Autowired
    public PersonService(PersonRepository repository) {
        this.repository = repository;
    }

    public Iterable<Person> getAll() {
        return repository.findAll();
    }

    public Optional<Person> getById(Long id) {
        return repository.findById(id);
    }

    public Person add(Person person) {
        return repository.save(person);
    }

    public Person edit(Person person) {
        Person editedPerson = getById(person.getId()).orElseThrow();

        editedPerson.setGender(person.getGender());
        editedPerson.setName(person.getName());
        editedPerson.setBirthDate(person.getBirthDate());
        editedPerson.setChildNum(person.getChildNum());

        return editedPerson;
    }

    public Optional<Person> getByLogin(String login) {
        return repository.findByLogin(login);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
