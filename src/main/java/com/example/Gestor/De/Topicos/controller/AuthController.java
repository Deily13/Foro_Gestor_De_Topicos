package com.example.Gestor.De.Topicos.controller;


import com.example.Gestor.De.Topicos.dto.LoginRequest;
import com.example.Gestor.De.Topicos.infra.security.DatosJWTToken;
import com.example.Gestor.De.Topicos.infra.security.TokenService;
import com.example.Gestor.De.Topicos.model.Usuario;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private TokenService tokenService;

    public AuthController(AuthenticationManager authenticationManager, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity autenticarUsuario(@RequestBody @Valid LoginRequest loginRequest) {
        Authentication authToken = new UsernamePasswordAuthenticationToken(
                loginRequest.correoElectronico(),
                loginRequest.contrasena()
        );
        var usuarioAutenticado = authenticationManager.authenticate(authToken);
        var jwtToken = tokenService.generarToken((Usuario) usuarioAutenticado.getPrincipal());
        return ResponseEntity.ok( new DatosJWTToken(jwtToken));


    }
}

