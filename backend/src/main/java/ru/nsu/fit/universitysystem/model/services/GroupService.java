package ru.nsu.fit.universitysystem.model.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nsu.fit.universitysystem.model.entities.Group;
import ru.nsu.fit.universitysystem.model.repositories.GroupRepository;

import java.util.Optional;

@Service
public class GroupService {
    private final GroupRepository repository;

    @Autowired
    public GroupService(GroupRepository repository) {
        this.repository = repository;
    }

    public Iterable<Group> getAll() {
        return repository.findAll();
    }

    public Optional<Group> getById(Long id) {
        return repository.findById(id);
    }

    public Group add(Group group) {
        return repository.save(group);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
