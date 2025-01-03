package com.example.Gestor.De.Topicos.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TopicoRequestDTO (

        @NotBlank
        private String titulo;

                @NotBlank
                private String mensaje;

                @NotNull
                private Long autorId;

                @NotNull
                private Long cursoId;
) {}
