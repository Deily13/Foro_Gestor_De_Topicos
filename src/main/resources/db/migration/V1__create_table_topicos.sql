CREATE TABLE topicos (
                         id SERIAL PRIMARY KEY,
                         titulo VARCHAR(255) NOT NULL,
                         mensaje TEXT NOT NULL,
                         fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                         status BOOLEAN DEFAULT TRUE NOT NULL,
                         autor_id BIGINT NOT NULL, -- Cambiado a autor_id para hacer referencia a la clave primaria de usuarios
                         curso_id BIGINT NOT NULL, -- Cambiado a curso_id para hacer referencia a la clave primaria de cursos
                         respuestas JSONB,
                         CONSTRAINT fk_autor FOREIGN KEY (autor_id) REFERENCES usuario(id), -- Clave foránea para autor
                         CONSTRAINT fk_curso FOREIGN KEY (curso_id) REFERENCES curso(id) -- Clave foránea para curso
);
