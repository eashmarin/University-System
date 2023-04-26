package ru.nsu.fit.universitysystem.model.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nsu.fit.universitysystem.model.entities.Student;
import ru.nsu.fit.universitysystem.model.repositories.StudentRepository;

import java.util.Optional;
import java.util.Random;

@Service
public class StudentService {
    private final StudentRepository repository;

    @Autowired
    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    public Iterable<Student> getAll() {
        return repository.findAll();
    }

    public Optional<Student> getById(Long id) {
        return repository.findById(id);
    }

    public Student add(Student student) {
        String login = generateLogin();
        String password = generatePassword();

        student.setLogin(login);
        student.setPassword(password);

        return repository.save(student);
    }

    private String generateLogin() {
        String login = "";
        while (login.length() < 6 || loginExists(login)) {
            Random random = new Random();
            login += (char) ('a' + random.nextInt(('z' - 'a') + 1));
        }

        return login;
    }

    private String generatePassword() {
        StringBuilder password = new StringBuilder();
        while (password.length() < 8) {
            Random random = new Random();
            int k = random.nextInt(3);
            char nextSymbol = switch (k) {
                case 0 -> (char) ('a' + random.nextInt(('z' - 'a') + 1));
                case 1 -> (char) ('A' + random.nextInt(('Z' - 'A') + 1));
                case 2 -> (char) ('0' + random.nextInt(('9' - '0') + 1));
                default -> Character.MIN_VALUE;
            };
            password.append(nextSymbol);
        }

        return password.toString();
    }

    private boolean loginExists(String login) {
        return repository.existsByLogin(login);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
