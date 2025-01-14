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

        private String status = String.valueOf(true);

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "autor_id", nullable = false)
        private Usuario autor;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "curso_id", nullable = false)
        private Curso curso;

        @OneToMany(mappedBy = "topico", cascade = CascadeType.ALL, orphanRemoval = true)
        private List<Respuesta> respuestas;

}
