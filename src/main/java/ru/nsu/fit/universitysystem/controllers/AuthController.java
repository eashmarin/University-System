package ru.nsu.fit.universitysystem.controllers;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.nsu.fit.universitysystem.model.dtos.CredentialsDto;
import ru.nsu.fit.universitysystem.model.dtos.UserDto;
import ru.nsu.fit.universitysystem.model.entities.Person;
import ru.nsu.fit.universitysystem.model.services.AuthenticationService;
import ru.nsu.fit.universitysystem.model.services.PersonService;

@RestController
@RequestMapping("/api")
public class AuthController {
    private final AuthenticationService authenticationService;

    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody CredentialsDto credentialsDto, HttpServletResponse response) {
        return authenticationService.logIn(credentialsDto, response);
    }

    @GetMapping("/check-auth")
    public ResponseEntity<String> checkAuth() {
        return ResponseEntity.ok().build(); // filter return 403 before endpoint reach, so no reason to check here
    }

    /*@PostMapping("/register")
    public Person register(@RequestBody Person person) {
        return personService.add(person);
    }*/

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@AuthenticationPrincipal UserDto userDto) {
        SecurityContextHolder.clearContext();
        return ResponseEntity.noContent().build();
    }

}
