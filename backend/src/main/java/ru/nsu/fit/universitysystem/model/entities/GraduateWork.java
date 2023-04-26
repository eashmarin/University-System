package ru.nsu.fit.universitysystem.model.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "graduate_work", schema = "public")
public class GraduateWork {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "student_id")
    private Student student;
    @OneToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;
    private String name;

    public GraduateWork() {
    }

    public GraduateWork(Student student, Teacher teacher, String name) {
        this.student = student;
        this.teacher = teacher;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
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
}
