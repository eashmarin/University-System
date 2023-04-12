package ru.nsu.fit.universitysystem.model.entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "class", schema = "public")
public class Class {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "teacher_id", nullable = false)
    private Teacher teacher;
    @ManyToOne
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;
    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;
    private String type;
    @Column(name = "start_date")
    private Date startDate;
    @Column(name = "finish_date")
    private Date finishDate;

    public Class() {}
    public Class(Teacher teacher, Subject subject, Group group, String type, Date startDate, Date finishDate) {
        this.teacher = teacher;
        this.subject = subject;
        this.group = group;
        this.type = type;
        this.startDate = startDate;
        this.finishDate = finishDate;
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

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }
}
