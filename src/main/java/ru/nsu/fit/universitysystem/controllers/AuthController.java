package ru.nsu.fit.universitysystem.controllers;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.nsu.fit.universitysystem.dtos.CredentialsDto;
import ru.nsu.fit.universitysystem.dtos.UserDto;
import ru.nsu.fit.universitysystem.entities.Person;
import ru.nsu.fit.universitysystem.services.AuthenticationService;
import ru.nsu.fit.universitysystem.services.PersonService;

@RestController
@RequestMapping("/api")
public class AuthController {
    private final AuthenticationService authenticationService;
    private final PersonService personService;


    public AuthController(AuthenticationService authenticationService, PersonService personService, PasswordEncoder passwordEncoder) {
        this.authenticationService = authenticationService;
        this.personService = personService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody CredentialsDto credentialsDto, HttpServletResponse response) {
        return authenticationService.logIn(credentialsDto, response);
        /*Cookie authCookie = new Cookie(CookieAuthenticationFilter.COOKIE_NAME, authenticationService.createToken(userDto));
        authCookie.setHttpOnly(true);
        authCookie.setSecure(true);
        authCookie.setMaxAge((int) Duration.of(1, ChronoUnit.DAYS).toDays());
        authCookie.setPath("/");

        response.addCookie(authCookie);

        return ResponseEntity.ok(userDto);*/
    }

    @PostMapping("/register")
    public Person register(@RequestBody Person person) {
        return personService.add(person);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@AuthenticationPrincipal UserDto userDto) {
        SecurityContextHolder.clearContext();
        return ResponseEntity.noContent().build();
    }

}
