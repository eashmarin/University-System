package ru.nsu.fit.universitysystem.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.nsu.fit.universitysystem.security.Role;

import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class TokenUtil {

    @Value("${jwt.secret}")
    private String secretKey;
    public final static String TOKEN_COOKIE_NAME = "token";

    public String getLoginFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    public Cookie createTokenCookie(String login) {
        Cookie cookie = new Cookie(TOKEN_COOKIE_NAME, generateToken(login, null));
        cookie.setMaxAge((int) TimeUnit.DAYS.toSeconds(1));
        cookie.setSecure(true);
        cookie.setPath("/");
        return cookie;
    }

    private String generateToken(String login, List<Role> roles) {
        return Jwts.builder()
                .setSubject(login)
                .claim("roles", roles)
                .setIssuedAt(new Date())
                .setExpiration(new Date(
                        System.currentTimeMillis() + ChronoUnit.DAYS.getDuration().toMillis()
                ))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }
}
