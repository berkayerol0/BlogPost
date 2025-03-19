package com.example.blogpost.berkayerol.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.util.Date;

@Service
public class JwtService {

    private static final String SECRET_KEY = "JWT_SECRET_KEY";

    private static final int JWTExpireDuration = 1;

    //Token oluşturma
    public String generateJwtToken(UserDetails userDetails) {
        return JWT.create()
                .withSubject(userDetails.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + 3600L * 1000 * 24 * 7))
                .withIssuer("BerkayErol")
                .withIssuedAt(Instant.now())
                .sign(Algorithm.HMAC256(SECRET_KEY));
    }

    //Token geçerliliğini kontrol etme
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    //Token'ın süresinin dolup dolmadığını kontrol etme
    private boolean isTokenExpired(String token) { return extractExpiration(token).before(new Date()); }

    //Token'dan kullanıcı adını alma
    public String extractUsername(String token) { return decodeToken(token).getSubject(); }

    //Tokendan son kullanma tarihini alma
    private Date extractExpiration(String token) { return decodeToken(token).getExpiresAt(); }

    //Tokenı decode etme
    private DecodedJWT decodeToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer("BerkayErol")
                .build();
        return verifier.verify(token);
    }
}
