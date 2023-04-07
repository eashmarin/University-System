package ru.nsu.fit.universitysystem.entities;

import jakarta.persistence.*;
import ru.nsu.fit.universitysystem.security.Role;

import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "person", schema = "public")
@Inheritance (strategy = InheritanceType.JOINED)
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String login;
    private String password;
    private String name;
    private String gender;
    @Column(name = "birth_date")
    private Date birthDate;
    @Column(name = "child_num")
    private Integer childNum;
    private String role = Role.USER.name();

    public Person() {}

    public Person(String login, String password, String name, String gender, Date birthDate, Integer childNum, Role role) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.gender = gender;
        this.birthDate = birthDate;
        this.childNum = childNum;
        this.role = role.name();
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Integer getChildNum() {
        return childNum;
    }

    public void setChildNum(Integer childNum) {
        this.childNum = childNum;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return id.equals(person.id) && name.equals(person.name) && gender.equals(person.gender) && birthDate.equals(person.birthDate) && childNum.equals(person.childNum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, gender, birthDate, childNum);
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", birthDate=" + birthDate +
                ", childNum=" + childNum +
                '}';
    }

    public Role getRole() {
        return Role.valueOf(role);
    }

    public void setRole(Role role) {
        this.role = role.name();
    }
}