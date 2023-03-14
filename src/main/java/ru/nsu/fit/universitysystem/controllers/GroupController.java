package ru.nsu.fit.universitysystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.nsu.fit.universitysystem.entities.Group;
import ru.nsu.fit.universitysystem.entities.Student;
import ru.nsu.fit.universitysystem.services.GroupService;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/groups")
public class GroupController {
    private final GroupService service;

    @Autowired
    public GroupController(GroupService service) {
        this.service = service;
    }

    @GetMapping
    public Iterable<Group> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Group> getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping("/{id}/list")
    public Collection<Student> getStudents(@PathVariable Long id) {
        return getById(id).get().getStudents();
    }
}