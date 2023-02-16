package ru.nsu.fit.universitysystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.nsu.fit.universitysystem.entities.Person;
import ru.nsu.fit.universitysystem.services.PersonService;

import java.util.Optional;

@RestController
@RequestMapping("/api/persons")
public class PersonController {
    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public Iterable<Person> getAll() {
        return personService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Person> getById(@PathVariable Long id) {
        return personService.getById(id);
    }

    @PostMapping
    public Person add(@RequestBody Person person) {
        return personService.add(person);
    }

    @PutMapping
    public Person edit(@RequestBody Person person) {
        return personService.edit(person);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        personService.delete(id);
    }
}
