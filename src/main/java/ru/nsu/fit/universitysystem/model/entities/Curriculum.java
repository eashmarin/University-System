package ru.nsu.fit.universitysystem.model.entities;

import jakarta.persistence.*;

import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name = "curriculum", schema = "public")
public class Curriculum {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;
    @Column(name = "lecture_hours")
    private Integer lectureHours;
    @Column(name = "seminar_hours")
    private Integer seminarHours;
    @Column(name = "laboratory_hours")
    private Integer laboratoryHours;
    @Column(name = "consult_hours")
    private Integer consultHours;
    @Column(name = "coursework_hours")
    private Integer courseworkHours;
    @Column(name = "individual_hours")
    private Integer individualHours;
    private int[] semesters;

    public Curriculum() {}

    public Curriculum(Subject subject, Integer lectureHours, Integer seminarHours,
                      Integer laboratoryHours, Integer consultHours, Integer courseworkHours,
                      Integer individualHours, int[] semesters) {
        this.subject = subject;
        this.lectureHours = lectureHours;
        this.seminarHours = seminarHours;
        this.laboratoryHours = laboratoryHours;
        this.consultHours = consultHours;
        this.courseworkHours = courseworkHours;
        this.individualHours = individualHours;
        this.semesters = semesters;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Integer getLectureHours() {
        return lectureHours;
    }

    public void setLectureHours(Integer lectureHours) {
        this.lectureHours = lectureHours;
    }

    public Integer getSeminarHours() {
        return seminarHours;
    }

    public void setSeminarHours(Integer seminarHours) {
        this.seminarHours = seminarHours;
    }

    public Integer getLaboratoryHours() {
        return laboratoryHours;
    }

    public void setLaboratoryHours(Integer laboratoryHours) {
        this.laboratoryHours = laboratoryHours;
    }

    public Integer getConsultHours() {
        return consultHours;
    }

    public void setConsultHours(Integer consultHours) {
        this.consultHours = consultHours;
    }

    public Integer getCourseworkHours() {
        return courseworkHours;
    }

    public void setCourseworkHours(Integer courseworkHours) {
        this.courseworkHours = courseworkHours;
    }

    public Integer getIndividualHours() {
        return individualHours;
    }

    public void setIndividualHours(Integer individualHours) {
        this.individualHours = individualHours;
    }

    public int[] getSemesters() {
        return semesters;
    }

    public void setSemesters(int[] semesters) {
        this.semesters = semesters;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Curriculum that = (Curriculum) o;

        if (!id.equals(that.id)) return false;
        if (!subject.equals(that.subject)) return false;
        if (!Objects.equals(lectureHours, that.lectureHours)) return false;
        if (!Objects.equals(seminarHours, that.seminarHours)) return false;
        if (!Objects.equals(laboratoryHours, that.laboratoryHours))
            return false;
        if (!Objects.equals(consultHours, that.consultHours)) return false;
        if (!Objects.equals(courseworkHours, that.courseworkHours))
            return false;
        if (!Objects.equals(individualHours, that.individualHours))
            return false;
        return Arrays.equals(semesters, that.semesters);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + subject.hashCode();
        result = 31 * result + (lectureHours != null ? lectureHours.hashCode() : 0);
        result = 31 * result + (seminarHours != null ? seminarHours.hashCode() : 0);
        result = 31 * result + (laboratoryHours != null ? laboratoryHours.hashCode() : 0);
        result = 31 * result + (consultHours != null ? consultHours.hashCode() : 0);
        result = 31 * result + (courseworkHours != null ? courseworkHours.hashCode() : 0);
        result = 31 * result + (individualHours != null ? individualHours.hashCode() : 0);
        result = 31 * result + Arrays.hashCode(semesters);
        return result;
    }

    @Override
    public String toString() {
        return "Curriculum{" +
                "id=" + id +
                ", subject=" + subject +
                ", lectureHours=" + lectureHours +
                ", seminarHours=" + seminarHours +
                ", laboratoryHours=" + laboratoryHours +
                ", consultHours=" + consultHours +
                ", courseworkHours=" + courseworkHours +
                ", individualHours=" + individualHours +
                ", semesters=" + Arrays.toString(semesters) +
                '}';
    }
}
