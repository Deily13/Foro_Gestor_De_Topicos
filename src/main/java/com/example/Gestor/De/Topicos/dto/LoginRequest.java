package com.example.Gestor.De.Topicos.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
        @NotBlank @Email (message = "Username is required") String correoElectronico,
        @NotBlank(message = "Password is required") String contrasena
) {}
