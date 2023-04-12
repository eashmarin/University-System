package ru.nsu.fit.universitysystem.model.entities;

import jakarta.persistence.*;
import ru.nsu.fit.universitysystem.model.enums.EducationLevel;
import ru.nsu.fit.universitysystem.model.enums.Gender;
import ru.nsu.fit.universitysystem.model.enums.Role;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "student", schema = "public")
@PrimaryKeyJoinColumn(name = "person_id")
public class Student extends Person {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    private Group group;
    @Enumerated(EnumType.STRING)
    @Column(name = "education_level")
    private EducationLevel educationLevel;
    private BigDecimal scholarship;

    @OneToMany(mappedBy = "student")
    private Collection<RecordsBook> recordsBooks;

    @OneToOne(mappedBy = "student")
    private GraduateWork graduateWork;

    public Student() {
    }

    public Student(String login, String password, String name, Gender gender, Date birthDate, Integer childNum, Group group, EducationLevel educationLevel, BigDecimal scholarship, Role role) {
        super(login, password, name, gender, birthDate, childNum, role);
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

    public EducationLevel getEducationLevel() {
        return educationLevel;
    }

    public void setEducationLevel(EducationLevel educationLevel) {
        this.educationLevel = educationLevel;
    }

    public BigDecimal getScholarship() {
        return scholarship;
    }

    public void setScholarship(BigDecimal scholarship) {
        this.scholarship = scholarship;
    }

    @Override
    public String toString() {
        return "Student{" +
                "group=" + group +
                ", educationLevel='" + educationLevel + '\'' +
                ", scholarship=" + scholarship +
                ", recordsBooks=" + recordsBooks +
                ", graduateWork=" + graduateWork +
                '}';
    }
}
