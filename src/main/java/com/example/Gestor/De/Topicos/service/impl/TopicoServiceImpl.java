package com.example.Gestor.De.Topicos.service.impl;

import com.example.Gestor.De.Topicos.dto.TopicoRequestDTO;
import com.example.Gestor.De.Topicos.model.Topico;
import com.example.Gestor.De.Topicos.repository.TopicoRepository;
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
        if (topicoRepository.existsByTituloAndMensaje(dto.getTitulo(), dto.getMensaje())) {
            throw new IllegalArgumentException("Tópico duplicado.");
        }

        Usuario autor = usuarioRepository.findById(dto.getAutorId())
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado."));
        Curso curso = cursoRepository.findById(dto.getCursoId())
                .orElseThrow(() -> new IllegalArgumentException("Curso no encontrado."));

        Topico topico = new Topico();
        topico.setTitulo(dto.getTitulo());
        topico.setMensaje(dto.getMensaje());
        topico.setAutor(autor);
        topico.setCurso(curso);

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
}
