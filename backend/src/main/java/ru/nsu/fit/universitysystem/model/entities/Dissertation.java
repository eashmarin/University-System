package ru.nsu.fit.universitysystem.model.entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "dissertation", schema = "public")
public class Dissertation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "teacher_id", nullable = false)
    private Teacher teacher;
    private String name;
    private String type;
    private Date date;

    public Dissertation() {
    }

    public Dissertation(Teacher teacher, String name, String type, Date date) {
        this.teacher = teacher;
        this.name = name;
        this.type = type;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
