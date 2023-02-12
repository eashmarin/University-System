package ru.nsu.fit.universitysystem.entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "subject", schema = "public")
public class Subject {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @Column(name = "control_form")
    private String controlForm;

    @OneToOne(optional = false, mappedBy = "subject")
    private Curriculum curriculum;

    public Subject() {}

    public Subject(String name, String controlForm) {
        this.name = name;
        this.controlForm = controlForm;
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

    public String getControlForm() {
        return controlForm;
    }

    public void setControlForm(String controlForm) {
        this.controlForm = controlForm;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subject subject = (Subject) o;
        return id.equals(subject.id) && name.equals(subject.name) && controlForm.equals(subject.controlForm);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, controlForm);
    }

    @Override
    public String toString() {
        return "Subject{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", controlForm='" + controlForm + '\'' +
                '}';
    }
}
