package com.example.Gestor.De.Topicos.infra.security;

import com.example.Gestor.De.Topicos.repository.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {


    private final TokenService tokenService;
    private final UsuarioRepository usuarioRepository;

    public SecurityFilter(TokenService tokenService, UsuarioRepository usuarioRepository) {
        this.tokenService = tokenService;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        System.out.println("Inicio doFilter");

        var authHeader = request.getHeader("Authorization");
        if (authHeader != null) {
            System.out.println("Encabezado auth: " + authHeader);
            var token = authHeader.replace("Bearer ", "");
            System.out.println("Token extraído: " + token);
            var correoElectronico = tokenService.getSubject(token); // extract username
            System.out.println("Correo electrónico extraído: " + correoElectronico);
            if (correoElectronico != null) {
                // Token valido
                var usuario = usuarioRepository.findByCorreoElectronico(correoElectronico);
                var authentication = new UsernamePasswordAuthenticationToken(usuario, null,
                        usuario.getAuthorities()); // Forzamos un inicio de sesion
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request, response);
    }
}
