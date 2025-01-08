package com.example.Gestor.De.Topicos.controller;


import com.example.Gestor.De.Topicos.dto.TopicoRequestDTO;
import com.example.Gestor.De.Topicos.dto.TopicoResponseDTO;
import com.example.Gestor.De.Topicos.model.Topico;
import com.example.Gestor.De.Topicos.service.TopicoService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    public Page<TopicoRequestDTO> obtenerTodosLosTopicos(
        @PageableDefault(size = 10, sort = "fechaCreacion", direction = Sort.Direction.ASC) Pageable pageable) {
            return topicoService.obtenerTodosLosTopicos(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicoResponseDTO> obtenerTopicoPorId(@PathVariable Long id) {
        TopicoResponseDTO respuesta = topicoService.obtenerTopicoPorId(id);
        return ResponseEntity.ok(respuesta);
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
