package ru.nsu.fit.universitysystem.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "faculty", schema = "public")
public class Faculty {
    @Id
    @GeneratedValue
    private Long id;
    private String name;

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
}
