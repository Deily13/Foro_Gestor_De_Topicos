CREATE TABLE topicos (
                         id SERIAL PRIMARY KEY, -- Llave primaria numérica autoincremental
                         titulo VARCHAR(255) NOT NULL, -- Título del tópico
                         mensaje TEXT NOT NULL, -- Mensaje del tópico
                         fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Fecha de creación con un valor por defecto
                         status BOOLEAN DEFAULT TRUE NOT NULL, -- Estado del tópico: activo (TRUE) o desactivado (FALSE)
                         autor VARCHAR(100) NOT NULL, -- Autor del tópico
                         curso VARCHAR(100) NOT NULL, -- Curso relacionado
                         respuestas JSONB -- Respuestas en formato JSON (puedes ajustar si necesitas otro tipo)
);
