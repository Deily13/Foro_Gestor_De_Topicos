package com.example.Gestor.De.Topicos.service.impl;

import com.example.Gestor.De.Topicos.dto.TopicoRequestDTO;
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
    public Topico crearTopico(TopicoRequestDTO dto) {
        // Verifica si el tópico ya existe basado en el título y el mensaje
        if (topicoRepository.existsByTituloAndMensaje(dto.titulo(), dto.mensaje())) {
            throw new IllegalArgumentException("Tópico duplicado.");
        }

        // Busca el autor por ID, lanza excepción si no se encuentra
        Usuario autor = usuarioRepository.findById(dto.autorId())
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado."));

        // Busca el curso por ID, lanza excepción si no se encuentra
        Curso curso = cursoRepository.findById(dto.cursoId())
                .orElseThrow(() -> new IllegalArgumentException("Curso no encontrado."));
        // Crea un nuevo tópico y asigna los valores
        Topico topico = new Topico();
        topico.setTitulo(dto.titulo());
        topico.setMensaje(dto.mensaje());
        topico.setAutor(autor);
        topico.setCurso(curso);

        // Guarda el tópico en el repositorio y lo retorna
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
                topico.getAutor().getId(),
                topico.getCurso().getId()
        ));
    }

    @Override
    public Topico obtenerTopicoPorId(Long id) {
        return topicoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Tópico no encontrado."));
    }

    @Override
    public TopicoRequestDTO actualizarTopico(Long id, TopicoRequestDTO dto) {
        // Busca el tópico existente por ID
        Topico topico = topicoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Tópico no encontrado."));

        // Actualiza los campos del tópico
        topico.setTitulo(dto.titulo());
        topico.setMensaje(dto.mensaje());
        topico.setAutor(usuarioRepository.findById(dto.autorId())
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado.")));
        topico.setCurso(cursoRepository.findById(dto.cursoId())
                .orElseThrow(() -> new IllegalArgumentException("Curso no encontrado.")));

        // Guarda los cambios en la base de datos
        Topico topicoActualizado = topicoRepository.save(topico);

        return dto;
    }



    @Override
    public void eliminarTopico(Long id) {
        topicoRepository.deleteById(id);
    }
}

