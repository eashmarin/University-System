package ru.nsu.fit.universitysystem.services;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.nsu.fit.universitysystem.dtos.CredentialsDto;
import ru.nsu.fit.universitysystem.entities.Person;
import ru.nsu.fit.universitysystem.utils.TokenUtil;

import java.util.Optional;

@Service
public class AuthenticationService {


    private final PersonService personService;
    private final TokenUtil tokenUtil;
    private final PasswordEncoder passwordEncoder;

    public AuthenticationService(PersonService personService, TokenUtil tokenUtil, PasswordEncoder passwordEncoder) {
        this.personService = personService;
        this.tokenUtil = tokenUtil;
        this.passwordEncoder = passwordEncoder;
    }

    public ResponseEntity<String> authenticate(String token) {
        String userLogin = tokenUtil.getLoginFromToken(token);
        if (userLogin == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(userLogin);
    }

    public ResponseEntity<String> logIn(CredentialsDto credentialsDto, HttpServletResponse response) {
        if (validateCredentials(credentialsDto)) {
            Cookie tokenCookie = tokenUtil.createTokenCookie(credentialsDto.getLogin());
            response.addCookie(tokenCookie);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    private boolean validateCredentials(CredentialsDto credentialsDto) {
        Optional<Person> person = personService.getByLogin(credentialsDto.getLogin());
        if (person.isEmpty() || !passwordEncoder.matches(credentialsDto.getPassword(), person.get().getPassword())) {
            return false;
        }

        return true;
    }



    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(8);
    }
}
