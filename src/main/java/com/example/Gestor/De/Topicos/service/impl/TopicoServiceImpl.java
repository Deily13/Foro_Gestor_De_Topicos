package com.example.Gestor.De.Topicos.service.impl;

import com.example.Gestor.De.Topicos.dto.TopicoRequestDTO;
import com.example.Gestor.De.Topicos.dto.TopicoResponseDTO;
import com.example.Gestor.De.Topicos.model.Curso;
import com.example.Gestor.De.Topicos.model.Topico;
import com.example.Gestor.De.Topicos.model.Usuario;
import com.example.Gestor.De.Topicos.repository.CursoRepository;
import com.example.Gestor.De.Topicos.repository.TopicoRepository;
import com.example.Gestor.De.Topicos.repository.UsuarioRepository;
import com.example.Gestor.De.Topicos.service.TopicoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class TopicoServiceImpl implements TopicoService {

    private final TopicoRepository topicoRepository;
    private final UsuarioRepository usuarioRepository;
    private final CursoRepository cursoRepository;

    public TopicoServiceImpl(TopicoRepository topicoRepository, UsuarioRepository usuarioRepository, CursoRepository cursoRepository) {
        this.topicoRepository = topicoRepository;
        this.usuarioRepository = usuarioRepository;
        this.cursoRepository = cursoRepository;
    }

    @Override
    public Topico crearTopico(TopicoRequestDTO request) {

        Usuario autor = usuarioRepository.findById(request.autorId())
                .orElseThrow(() -> new IllegalArgumentException("Autor no encontrado"));

        Curso curso = cursoRepository.findById(request.cursoId())
                .orElseThrow(() -> new IllegalArgumentException("Curso no encontrado"));


        Topico topico = new Topico();
        topico.setTitulo(request.titulo());
        topico.setMensaje(request.mensaje());
        topico.setAutor(autor);
        topico.setCurso(curso);

        return topicoRepository.save(topico);
    }

/**
     * @param pageable
     * @return
     */
    @Override
    public Page<TopicoRequestDTO> obtenerTodosLosTopicos(Pageable pageable) {
        Page<Topico> topicosPaginados = topicoRepository.findAll(pageable);

        return topicosPaginados.map(topico -> new TopicoRequestDTO(
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getStatus(),
                topico.getAutor().getId(),
                topico.getCurso().getId()
        ));
    }

    @Override
    public TopicoResponseDTO obtenerTopicoPorId(Long id) {
        // Buscar el tópico por ID
        Topico topico = topicoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Tópico no encontrado."));

        // Mapear el modelo Topico al DTO TopicoResponseDTO
        return new TopicoResponseDTO(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion(),
                topico.getStatus(),
                topico.getAutor().getNombre(), // Asumiendo que Usuario tiene un atributo 'nombre'
                topico.getCurso().getNombre()  // Asumiendo que Curso tiene un atributo 'nombre'
        );
    }

    @Override
    public Topico actualizarTopico(Long id, Boolean status)
    {Topico topico = topicoRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Tópico no encontrado"));

        // Solo actualizar el estado
        topico.setStatus(String.valueOf(status));

        // Guardar el tópico actualizado en la base de datos
        return topicoRepository.save(topico);
    }


    @Override
    public void eliminarTopico(Long id) {
        topicoRepository.deleteById(id);
    }


}

