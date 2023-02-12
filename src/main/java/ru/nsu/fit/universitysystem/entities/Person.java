package ru.nsu.fit.universitysystem.entities;

import jakarta.persistence.*;

import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "person", schema = "public")
public class Person {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String gender;
    @Column(name = "birth_date")
    private Date birthDate;
    @Column(name = "child_num")
    private Integer childNum;

    public Person() {}

    public Person(String name, String gender, Date birthDate, Integer childNum) {
        this.name = name;
        this.gender = gender;
        this.birthDate = birthDate;
        this.childNum = childNum;
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
}
