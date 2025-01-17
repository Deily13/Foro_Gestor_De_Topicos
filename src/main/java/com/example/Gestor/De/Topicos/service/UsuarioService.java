package com.example.Gestor.De.Topicos.service;

import com.example.Gestor.De.Topicos.model.Usuario;

import java.util.Optional;

public interface UsuarioService {

    Usuario crearUsuario (Usuario usuario);
    Optional<Usuario> findByUsername(String username);
}
