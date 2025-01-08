package com.example.Gestor.De.Topicos.service;

import com.example.Gestor.De.Topicos.dto.TopicoRequestDTO;
import com.example.Gestor.De.Topicos.dto.TopicoResponseDTO;
import com.example.Gestor.De.Topicos.model.Topico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface TopicoService {
    Topico crearTopico(TopicoRequestDTO dto);
    Page<TopicoRequestDTO> obtenerTodosLosTopicos(Pageable pageable);
    TopicoResponseDTO obtenerTopicoPorId(Long id);
    TopicoRequestDTO actualizarTopico(Long id, TopicoRequestDTO dto);
    void eliminarTopico(Long id);
}
