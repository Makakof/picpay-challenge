package com.picpay_challenge.infra;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.picpay_challenge.entities.Person;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.toke.secret}") // Caminho de onde o código deve buscar a chave no properties
    private String secretKey;

    public String generateToken(Person person){
        try{
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            String token = JWT.create()
                    .withIssuer("auth-picpay") // quem ta gerando o token
                    .withSubject(person.getUsername()) // para quem ele foi gerado
                    .withExpiresAt(generateExpiredDate()) // quando ele vence
                    .sign(algorithm);

            return token;
        }catch(JWTCreationException exception){
            throw new RuntimeException("Error while generating token", exception);
        }
    }

    public String getSubject(String token){
        try{
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            return JWT.require(algorithm)
                    .withIssuer("auth-picpay") // quem gerou o token, no caso nossa aplicação
                    .build()
                    .verify(token) // descriptgrafa
                    .getSubject(); // pega quem mandou la de dentro
        }catch (JWTVerificationException exception){
            throw new RuntimeException("Error while subject of token", exception);
        }
    }

    private Instant generateExpiredDate(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
