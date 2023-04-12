package ru.nsu.fit.universitysystem.model.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nsu.fit.universitysystem.model.entities.Faculty;
import ru.nsu.fit.universitysystem.model.repositories.FacultyRepository;

import java.util.Optional;

@Service
public class FacultyService {
    private final FacultyRepository repository;

    @Autowired
    public FacultyService(FacultyRepository repository) {
        this.repository = repository;
    }

    public Iterable<Faculty> getAll() {
        return repository.findAll();
    }

    public Optional<Faculty> getById(Long id) {
        return repository.findById(id);
    }

    public Faculty add(Faculty group) {
        return repository.save(group);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
