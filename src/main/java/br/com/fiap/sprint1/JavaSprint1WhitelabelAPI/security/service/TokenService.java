package br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.security.service;


import br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.model.Customer;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;

@Service
public class TokenService {
    @Value("${api.token.secret}")
    private String tokenSecret;

    public String generateToken(Customer customer){
        try {
            Algorithm algorithm = Algorithm.HMAC256(this.tokenSecret);
            return JWT.create()
                    .withIssuer("FIAP-PLUSOFT")
                    .withSubject(customer.getEmail())
                    .withExpiresAt(Instant.now().plus(Duration.ofHours(2)))
                    .sign(algorithm);
        } catch (JWTCreationException e){
            throw new RuntimeException("Erro ao gerar token jwt");
        }
    }

    public String getSubject(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(this.tokenSecret);
            return JWT.require(algorithm)
                    .withIssuer("FIAP-PLUSOFT").
                    build().
                    verify(token).
                    getSubject();
        } catch (JWTVerificationException e){
            throw new RuntimeException("Não foi possível validar o TokenJWT");
        }
    }
}