package com.example.Gestor.De.Topicos.controller;


import com.example.Gestor.De.Topicos.dto.TopicoRequestDTO;
import com.example.Gestor.De.Topicos.model.Topico;
import com.example.Gestor.De.Topicos.service.TopicoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ControllerTopicos {

    private final TopicoService topicoService;

    public ControllerTopicos(TopicoService topicoService) {
        this.topicoService = topicoService;
    }

    @PostMapping
    public ResponseEntity<Topico> crearTopico(@Valid @RequestBody TopicoRequestDTO dto) {
        Topico topico = topicoService.crearTopico(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(topico);
    }

    @GetMapping
    public ResponseEntity<List<Topico>> obtenerTodosLosTopicos() {
        return ResponseEntity.ok(topicoService.obtenerTodosLosTopicos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Topico> obtenerTopicoPorId(@PathVariable Long id) {
        return ResponseEntity.ok(topicoService.obtenerTopicoPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TopicoRequestDTO> actualizarTopico(@PathVariable Long id, @Valid @RequestBody TopicoRequestDTO dto) {
        return ResponseEntity.ok(topicoService.actualizarTopico(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTopico(@PathVariable Long id) {
        topicoService.eliminarTopico(id);
        return ResponseEntity.noContent().build();
    }
}
