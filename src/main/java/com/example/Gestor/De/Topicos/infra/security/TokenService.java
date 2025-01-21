package com.example.Gestor.De.Topicos.infra.security;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.Gestor.De.Topicos.model.Usuario;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.secret}")
    private String apiSecret;

    @PostConstruct
    public void init() {
        // Verificar que el valor se inyectó correctamente

    }

    public String generarToken(Usuario usuario) {

        try {
            System.out.println("Secreto JWT al iniciar el servicio: " + apiSecret);
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            String token = JWT.create()
                    .withIssuer("gestor de topicos") // Issuer del token
                    .withSubject(usuario.getCorreoElectronico()) // Subject (correo electrónico)
                    .withClaim("contrasena", usuario.getContrasena()) // Campo opcional
                    .withExpiresAt(generarFechaExpiracion()) // Fecha de expiración
                    .sign(algorithm); // Firmar el token con el algoritmo y clave secreta

            // Imprime el token generado
            System.out.println("Token generado: " + token);

            return token;
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Error al generar el token", exception);
        }
    }

    public String getSubject(String token) {
        try {
            // Log para verificar que la clave secreta es correcta
            System.out.println("Clave secreta: " + apiSecret);

            // Decodificar y verificar el token
            Algorithm algorithm = Algorithm.HMAC256(apiSecret); // Usa la clave secreta proporcionada
            DecodedJWT verifier = JWT.require(algorithm)
                    .withIssuer("gestor de topicos") // Define el emisor esperado
                    .build()
                    .verify(token); // Verifica el token

            // Verifica si el sujeto (sub) no es nulo
            String subject = verifier.getSubject();
            if (subject == null) {
                // Si no se encuentra un sujeto válido, lanza una excepción
                throw new RuntimeException("El token no contiene un sujeto válido");
            }

            // Log para confirmar que el sujeto fue extraído correctamente
            System.out.println("Sujeto extraído: " + subject);

            // Devuelve el sujeto (correo electrónico, username, etc.)
            return subject;

        } catch (JWTVerificationException exception) {
            // Log para capturar errores relacionados con la validación del token
            System.out.println("Error al procesar el token: " + exception.getMessage());
            return null;

        } catch (IllegalArgumentException exception) {
            // Log para capturar errores generales, como argumentos inválidos
            System.out.println("Error con el token o la clave secreta: " + exception.getMessage());
            return null;
        }
    }


    public Instant generarFechaExpiracion() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-05:00"));
    }
}
