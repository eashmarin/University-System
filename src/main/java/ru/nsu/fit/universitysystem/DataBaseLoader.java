package ru.nsu.fit.universitysystem;

import ru.nsu.fit.universitysystem.entities.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.nsu.fit.universitysystem.repositories.PersonRepository;

import java.util.Date;

@Component
public class DataBaseLoader implements CommandLineRunner {
    private final PersonRepository repository;

    @Autowired
    public DataBaseLoader(PersonRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) throws Exception {
        this.repository.save(new Person("Frodo", "man", new Date(), 5));
    }
}
