package com.example.Gestor.De.Topicos.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;


import java.time.LocalDateTime;
import java.util.List;

@Table(name = "topicos")
@Entity(name = "Topico")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @NotBlank
        private String titulo;

        @NotBlank
        private String mensaje;

        @NotNull
        private LocalDateTime fechaCreacion = LocalDateTime.now();

        @NotBlank
        private String status;

        @ManyToOne
        private Usuario autor;

        @ManyToOne
        private Curso curso;

        @OneToMany(mappedBy = "topico")
        private List<Respuesta> respuestas;


}
