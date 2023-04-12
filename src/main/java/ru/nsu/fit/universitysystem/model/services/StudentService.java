package ru.nsu.fit.universitysystem.model.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nsu.fit.universitysystem.model.entities.Student;
import ru.nsu.fit.universitysystem.model.repositories.StudentRepository;

import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository repository;

    @Autowired
    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    public Iterable<Student> getAll() {
        return repository.findAll();
    }

    public Optional<Student> getById(Long id) {
        return repository.findById(id);
    }

    public Student add(Student student) {
        return repository.save(student);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
