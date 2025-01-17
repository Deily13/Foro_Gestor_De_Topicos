package com.example.Gestor.De.Topicos.service.impl;

import com.example.Gestor.De.Topicos.model.Usuario;
import com.example.Gestor.De.Topicos.repository.UsuarioRepository;
import com.example.Gestor.De.Topicos.service.UsuarioService;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Usuario crearUsuario(Usuario usuario) {

        if (usuario.getNombre() == null || usuario.getCorreoElectronico() == null || usuario.getContrasena() == null) {
            throw new IllegalArgumentException("Todos los campos son obligatorios.");
        }

        return usuarioRepository.save(usuario);
    }

    @Override
    public Optional<Usuario> findByUsername(String username) {
        return Optional.empty();
    }


}
