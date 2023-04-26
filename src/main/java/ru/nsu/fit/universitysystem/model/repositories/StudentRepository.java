package ru.nsu.fit.universitysystem.model.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.nsu.fit.universitysystem.model.entities.Student;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {
    boolean existsByLogin(String login);
}
