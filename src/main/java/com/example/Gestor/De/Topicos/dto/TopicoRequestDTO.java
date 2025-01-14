package com.example.Gestor.De.Topicos.dto;

import jakarta.validation.constraints.NotNull;

public record TopicoRequestDTO(
        @NotNull(message = "El título no puede ser nulo")
        String titulo,

        @NotNull(message = "El mensaje no puede ser nulo")
        String mensaje,

        String status,

        @NotNull(message = "El ID de usuario no puede ser nulo")
        Long autorId,

        @NotNull(message = "El ID de curso no puede ser nulo")
        Long cursoId
) {}