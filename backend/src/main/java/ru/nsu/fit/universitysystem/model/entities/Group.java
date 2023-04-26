package ru.nsu.fit.universitysystem.model.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Collection;

@Entity
@Table(name = "group", schema = "public")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "faculty_id")
    private Faculty faculty;

    private String name;

    private Integer course;

    @OneToMany(mappedBy = "group", fetch = FetchType.LAZY)
    private Collection<Student> students;

    @OneToMany(mappedBy = "group")
    private Collection<Class> classes;

    public Group() {
    }

    public Group(Faculty faculty, String name, Integer course) {
        this.faculty = faculty;
        this.name = name;
        this.course = course;
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

    public Integer getCourse() {
        return course;
    }

    public void setCourse(Integer course) {
        this.course = course;
    }

    @JsonIgnore
    public Collection<Student> getStudents() {
        return students;
    }
}