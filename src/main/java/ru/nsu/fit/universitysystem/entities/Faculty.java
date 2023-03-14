package ru.nsu.fit.universitysystem.entities;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
@Table(name = "faculty", schema = "public")
public class Faculty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "faculty")
    private Collection<Group> groups;

    @OneToMany(mappedBy = "faculty")
    private Collection<Department> departments;

    public Faculty() {}

    public Faculty(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<Group> getGroups() {
        return groups;
    }
}
