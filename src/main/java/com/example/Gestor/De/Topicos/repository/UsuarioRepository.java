package com.example.Gestor.De.Topicos.repository;

import com.example.Gestor.De.Topicos.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findById(Long id);


    UserDetails findByCorreoElectronico(String correoElectronico);
}
