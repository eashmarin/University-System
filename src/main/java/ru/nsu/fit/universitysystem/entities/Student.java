package ru.nsu.fit.universitysystem.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "student", schema = "public")
@PrimaryKeyJoinColumn(name = "person_id")
public class Student extends Person {
    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;
    @Column(name = "education_level")
    private String educationLevel;
    private BigDecimal scholarship;

    @OneToMany(mappedBy = "student")
    private Collection<RecordsBook> recordsBooks;

    @OneToOne(mappedBy = "student")
    private GraduateWork graduateWork;

    public Student() {
    }

    public Student(String name, String gender, Date birthDate, Integer childNum, Group group, String educationLevel, BigDecimal scholarship) {
        super(name, gender, birthDate, childNum);
        this.group = group;
        this.educationLevel = educationLevel;
        this.scholarship = scholarship;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public String getEducationLevel() {
        return educationLevel;
    }

    public void setEducationLevel(String educationLevel) {
        this.educationLevel = educationLevel;
    }

    public BigDecimal getScholarship() {
        return scholarship;
    }

    public void setScholarship(BigDecimal scholarship) {
        this.scholarship = scholarship;
    }
}
