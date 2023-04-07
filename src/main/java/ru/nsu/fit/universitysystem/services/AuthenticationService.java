package ru.nsu.fit.universitysystem.services;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.nsu.fit.universitysystem.dtos.CredentialsDto;
import ru.nsu.fit.universitysystem.entities.Person;

import java.util.Optional;

@Service
public class AuthenticationService {

    private final PersonService personService;

    public AuthenticationService(PersonService personService) {
        this.personService = personService;
    }

    public ResponseEntity<String> authenticate(CredentialsDto credentialsDto) {
        Optional<Person> person = personService.getByLogin(credentialsDto.getLogin());
        if (person.isEmpty() || !passwordEncoder().matches(credentialsDto.getPassword(), person.get().getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }

        return ResponseEntity.ok("Successfully authenticated");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(8);
    }
}
