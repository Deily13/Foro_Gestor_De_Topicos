package com.example.Gestor.De.Topicos.service;

import com.example.Gestor.De.Topicos.dto.TopicoRequestDTO;
import com.example.Gestor.De.Topicos.model.Topico;

import java.util.List;


public interface TopicoService {
    Topico crearTopico(TopicoRequestDTO dto);
    List<Topico> obtenerTodosLosTopicos();
    Topico obtenerTopicoPorId(Long id);
    Topico actualizarTopico(Long id, TopicoRequestDTO dto);
    void eliminarTopico(Long id);
}
