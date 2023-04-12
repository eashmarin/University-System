package ru.nsu.fit.universitysystem.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.nsu.fit.universitysystem.model.services.AuthenticationService;
import ru.nsu.fit.universitysystem.model.utils.TokenUtil;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

@Component
public class TokenAuthenticationFilter extends OncePerRequestFilter {

    private final AuthenticationService authenticationService;

    public TokenAuthenticationFilter(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if ("/api/login".equals(request.getRequestURI())) {
            filterChain.doFilter(request, response);
            return;
        }

        response.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "http://localhost:3000");
        response.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, "*");
        response.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS, "content-type");
        response.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS, "true");

        if (request.getMethod().equals(HttpMethod.OPTIONS.toString())) {
            filterChain.doFilter(request, response);
            return;
        }

        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        Optional<Cookie> tokenCookie = Arrays.stream(cookies)
                .filter(cookie -> TokenUtil.TOKEN_COOKIE_NAME.equals(cookie.getName()))
                .findFirst();

        if (tokenCookie.isPresent()) {
            String login = authenticationService.authenticate(tokenCookie.get().getValue()).getBody();
            request.setAttribute("login", login);
        }

        if (request.getAttribute("login") == null) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN );
        }

        filterChain.doFilter(request, response);
    }
}
