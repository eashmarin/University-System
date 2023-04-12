package ru.nsu.fit.universitysystem.model.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.Cookie;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;

import java.util.Date;
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

    public ResponseCookie createTokenCookie(String login) {
        ResponseCookie cookie = ResponseCookie.from(TOKEN_COOKIE_NAME, generateToken(login))
                .path("/")
                .sameSite("Lax")
                .maxAge((int) TimeUnit.DAYS.toSeconds(1))
                .secure(false)
                .build();
        return cookie;
    }

    private String generateToken(String login) {
        return Jwts.builder()
                .setSubject(login)
                .setIssuedAt(new Date())
                .setExpiration(new Date(
                        System.currentTimeMillis() + TimeUnit.DAYS.toMillis(1)
                ))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }
}
