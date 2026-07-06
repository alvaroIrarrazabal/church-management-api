package com.irarrazabal.iglesiaapi.infraestructure.security;


import com.irarrazabal.iglesiaapi.domain.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String secret_key;

    @Value("${jwt.expiration}")
    private long EXPIRATION;




    //obtener clave de firma
    private SecretKey getSignKey(){
        return Keys.hmacShaKeyFor(secret_key.getBytes());

    }

    //Generar token

    public String generateToken(User user) {

        return Jwts.builder()
                .subject(user.getEmail())
                .claim("role", "ROLE_" + user.getRol().name())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(getSignKey())
                .compact();
    }

    //Extrae todos los claims

    private Claims extractAllClaims(String token){

        return Jwts.parser()
                .verifyWith(getSignKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    //extraer el username

    public String extractUsername(String token){
        return extractAllClaims(token)
                .getSubject();
    }


//Pregunta si expiro el token
    private boolean isTokenExpired(String token){

        return extractAllClaims(token)
                .getExpiration()
                .before(new Date());
    }


    //validar token
    public boolean isTokenValid(String token, UserDetails userDetails)
    {
        final String username =
                extractUsername(token);

        return  username.equals(userDetails.getUsername()
        )&&
                !isTokenExpired(token);


    }






}
