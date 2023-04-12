package ru.nsu.fit.universitysystem.model.services;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.nsu.fit.universitysystem.model.dtos.CredentialsDto;
import ru.nsu.fit.universitysystem.model.entities.Person;
import ru.nsu.fit.universitysystem.model.utils.TokenUtil;

import java.util.Optional;

@Service
public class AuthenticationService {
    private final PersonService personService;
    private final TokenUtil tokenUtil;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(8);

    public AuthenticationService(PersonService personService, TokenUtil tokenUtil) {
        this.personService = personService;
        this.tokenUtil = tokenUtil;
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
            ResponseCookie tokenCookie = tokenUtil.createTokenCookie(credentialsDto.getLogin());
            response.setHeader(HttpHeaders.SET_COOKIE, tokenCookie.toString());
            response.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, "*");
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    private boolean validateCredentials(CredentialsDto credentialsDto) {
        Optional<Person> person = personService.getByLogin(credentialsDto.getLogin());
        return person.isPresent() && passwordEncoder.matches(credentialsDto.getPassword(), person.get().getPassword());
    }
}