package ru.nsu.fit.universitysystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.nsu.fit.universitysystem.model.entities.Student;
import ru.nsu.fit.universitysystem.model.services.StudentService;

import java.util.Optional;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    private final StudentService service;

    @Autowired
    public StudentController(StudentService service) {
        this.service = service;
    }

    @GetMapping
    public Iterable<Student> getAll() {
        return service.getAll();
    }

    @PostMapping
    public Student add(@RequestBody Student student) {
        return service.add(student);
    }

    @GetMapping("/{id}")
    public Optional<Student> getById(@PathVariable Long id) {
        return service.getById(id);
    }
}