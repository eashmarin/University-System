package ru.nsu.fit.universitysystem.entities;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
@Table(name = "department", schema = "public")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "faculty_id", nullable = false)
    private Faculty faculty;
    private String name;

    @OneToMany(mappedBy = "department")
    private Collection<Teacher> teachers;

    public Department() {
    }

    public Department(Faculty faculty, String name) {
        this.faculty = faculty;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
