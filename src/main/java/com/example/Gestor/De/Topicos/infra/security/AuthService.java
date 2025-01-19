package com.example.Gestor.De.Topicos.infra.security;


import com.example.Gestor.De.Topicos.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements UserDetailsService {

private UsuarioRepository usuarioRepository;
public AuthService(UsuarioRepository usuarioRepository) {
    this.usuarioRepository = usuarioRepository;
}


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usuarioRepository.findByCorreoElectronico(username);
    }
}
