package com.huijin.yummy.security.jwt;

import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Date;

@Service
public class JwtService {

    SecretKey key = Jwts.SIG.HS256.key().build();

    public String generateJwt(long id) {
        LocalDateTime now = LocalDateTime.now();

        try {
            return Jwts.builder()
                    .issuer("issuer")
                    .issuedAt(new Date())
                    .subject("subject")
                    .expiration(new Date(now.getMinute() + Duration.ofMinutes(30).toMillis()))
                    .claim("id", id)
                    .signWith(key)
                    .compact();
        } catch (IllegalStateException e) {
            throw new IllegalStateException("Failed to generate JWT");
        }
    }

    public Long verifyJwt(String header) {
        String prefix = "bearer ";

        if (header.startsWith(prefix)) {
            String jwt = header.substring(prefix.length());

            return Long.parseLong(Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(jwt)
                    .getPayload()
                    .get("id")
                    .toString());
        }

        throw new IllegalStateException("Input string does not start with 'bearer '.");
    }
}
