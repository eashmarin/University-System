package ru.nsu.fit.universitysystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.nsu.fit.universitysystem.entities.Faculty;
import ru.nsu.fit.universitysystem.entities.Group;
import ru.nsu.fit.universitysystem.services.FacultyService;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/api/faculties")
public class FacultyController {
    private final FacultyService service;

    @Autowired
    public FacultyController(FacultyService service) {
        this.service = service;
    }

    @GetMapping
    public Iterable<Faculty> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Faculty> getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping("/{id}/groups")
    public Collection<Group> getGroups(@PathVariable Long id) {
        return getById(id).get().getGroups();
    }
}