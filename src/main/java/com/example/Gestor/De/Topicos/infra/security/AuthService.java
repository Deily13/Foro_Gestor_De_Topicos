package com.example.Gestor.De.Topicos.infra.security;


import com.example.Gestor.De.Topicos.model.Usuario;
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
    public UserDetails loadUserByUsername(String correoElectronico) throws UsernameNotFoundException {
    Usuario usuario = usuarioRepository.findByCorreoElectronico(correoElectronico)
            .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con el correo electrónico: " + correoElectronico));

    return new org.springframework.security.core.userdetails.User(
            usuario.getCorreoElectronico(),
            usuario.getContrasena(),
            java.util.Collections.emptyList() // Puedes agregar roles o autoridades si corresponde
            );
}
}
