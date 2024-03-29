package ru.nsu.fit.universitysystem.model.entities;

import jakarta.persistence.*;
import ru.nsu.fit.universitysystem.model.enums.Gender;
import ru.nsu.fit.universitysystem.model.enums.Role;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "\"teacher\"", schema = "public")
@PrimaryKeyJoinColumn(name = "person_id")
public class Teacher extends Person {
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;
    private String post;
    private BigDecimal salary;

    @OneToMany(mappedBy = "teacher")
    private Collection<Class> classes;

    @OneToOne(mappedBy = "teacher")
    private GraduateWork graduateWork;

    @OneToMany(mappedBy = "teacher")
    private Collection<Dissertation> dissertations;

    public Teacher() {
    }

    public Teacher(String login, String password, String name, Gender gender, Date birthDate, Integer childNum, Department department, String post, BigDecimal salary, Role role) {
        super(login, password, name, gender, birthDate, childNum, role);
        this.department = department;
        this.post = post;
        this.salary = salary;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }
}
