package com.example.Gestor.De.Topicos.service.impl;

import com.example.Gestor.De.Topicos.dto.TopicoRequestDTO;
import com.example.Gestor.De.Topicos.model.Curso;
import com.example.Gestor.De.Topicos.model.Topico;
import com.example.Gestor.De.Topicos.model.Usuario;
import com.example.Gestor.De.Topicos.repository.CursoRepository;
import com.example.Gestor.De.Topicos.repository.TopicoRepository;
import com.example.Gestor.De.Topicos.repository.UsuarioRepository;
import com.example.Gestor.De.Topicos.service.TopicoService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TopicoServiceImpl implements TopicoService {

    private final TopicoRepository topicoRepository;
    private final UsuarioRepository usuarioRepository; // Requiere implementación.
    private final CursoRepository cursoRepository;     // Requiere implementación.

    public TopicoServiceImpl(TopicoRepository topicoRepository, UsuarioRepository usuarioRepository, CursoRepository cursoRepository) {
        this.topicoRepository = topicoRepository;
        this.usuarioRepository = usuarioRepository;
        this.cursoRepository = cursoRepository;
    }

    @Override
    public Topico crearTopico(TopicoRequestDTO dto) {
        // Verifica si el tópico ya existe basado en el título y el mensaje
        if (topicoRepository.existsByTituloAndMensaje(dto.titulo(), dto.mensaje())) {
            throw new IllegalArgumentException("Tópico duplicado.");
        }

        /*
        // Busca el autor por ID, lanza excepción si no se encuentra
        Usuario autor = usuarioRepository.findById(dto.autorId())
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado."));

        // Busca el curso por ID, lanza excepción si no se encuentra
        Curso curso = cursoRepository.findById(dto.cursoId())
                .orElseThrow(() -> new IllegalArgumentException("Curso no encontrado."));
        */
        // Crea un nuevo tópico y asigna los valores
        Topico topico = new Topico();
        topico.setTitulo(dto.titulo());
        topico.setMensaje(dto.mensaje());
        //topico.setAutor(autor);
        //topico.setCurso(curso);

        // Guarda el tópico en el repositorio y lo retorna
        return topicoRepository.save(topico);
    }

    @Override
    public List<Topico> obtenerTodosLosTopicos() {
        return topicoRepository.findAll();
    }

    @Override
    public Topico obtenerTopicoPorId(Long id) {
        return topicoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Tópico no encontrado."));
    }

    @Override
    public Topico actualizarTopico(Long id, TopicoRequestDTO dto) {
        Topico topico = obtenerTopicoPorId(id);

        topico.setTitulo(dto.getTitulo());
        topico.setMensaje(dto.getMensaje());
        topico.setAutor(usuarioRepository.findById(dto.getAutorId())
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado.")));
        topico.setCurso(cursoRepository.findById(dto.getCursoId())
                .orElseThrow(() -> new IllegalArgumentException("Curso no encontrado.")));

        return topicoRepository.save(topico);
    }

    @Override
    public void eliminarTopico(Long id) {
        topicoRepository.deleteById(id);
    }
}

