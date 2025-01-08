package com.example.Gestor.De.Topicos.service;

import com.example.Gestor.De.Topicos.dto.TopicoRequestDTO;
import com.example.Gestor.De.Topicos.model.Topico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface TopicoService {
    Topico crearTopico(TopicoRequestDTO dto);
    Page<TopicoRequestDTO> obtenerTodosLosTopicos(Pageable pageable);
    Topico obtenerTopicoPorId(Long id);
    TopicoRequestDTO actualizarTopico(Long id, TopicoRequestDTO dto);
    void eliminarTopico(Long id);
}
