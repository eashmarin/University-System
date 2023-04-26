package ru.nsu.fit.universitysystem.model.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nsu.fit.universitysystem.model.entities.Person;
import ru.nsu.fit.universitysystem.model.repositories.PersonRepository;
import ru.nsu.fit.universitysystem.model.repositories.custom.CustomPersonRepository;

import java.util.Optional;

@Service
public class PersonService {
    private final PersonRepository repository;

    //private final CustomPersonRepository customRepository;

    @Autowired
    public PersonService(PersonRepository repository) {//, CustomPersonRepository customRepository) {
        this.repository = repository;
        //this.customRepository = customRepository;
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
        //return Optional.ofNullable(customRepository.findByLogin(login));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
