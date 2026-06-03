package com.irarrazabal.iglesiaapi;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.crypto.SecretKey;

@SpringBootApplication
public class IglesiaApiApplication {

    public static void main(String[] args) {


        SpringApplication.run(IglesiaApiApplication.class, args);
    }

}
