package ru.nsu.fit.universitysystem.model.entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "records_book", schema = "public")
public class RecordsBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;
    @ManyToOne
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;
    private Integer semester;
    private Date date;
    private String grade;

    public RecordsBook() {
    }

    public RecordsBook(Student student, Subject subject, Integer semester, Date date, String grade) {
        this.student = student;
        this.subject = subject;
        this.semester = semester;
        this.date = date;
        this.grade = grade;
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

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Integer getSemester() {
        return semester;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}
