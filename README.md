# Foro_Gestor_De_Topicos

Gestor de Tópicos

Este proyecto es una API que permite gestionar tópicos en una aplicación. Incluye funcionalidades para crear, listar, buscar, modificar y eliminar tópicos, 
cumpliendo con validaciones específicas y garantizando la integridad de los datos. La aplicación ha sido desarrollada utilizando Spring Boot y PostgreSQL 
como gestor base de datos, y su funcionalidad ha sido probada con Postman.


Funcionalidades

1. Crear Tópicos

Validaciones:

No permite registrar tópicos duplicados (mismo título y mensaje).

Los campos fundamentales como título, mensaje, ID de usuario y ID de curso no deben estar vacíos.

2. Listar Tópicos

Características:

Devuelve la lista de tópicos de forma paginada.

Ordena los resultados en forma ascendente por la fecha de creación.

3. Buscar un Tópico por ID

Funcionalidad:

Permite buscar un tópico específico utilizando su ID.

Si el tópico no existe, devuelve un error.

4. Modificar el Estado de un Tópico

Funcionalidad:

Permite modificar solo el estado (activo o inactivo) de un tópico específico por su ID.

5. Eliminar un Tópico

Funcionalidad:

Permite eliminar un tópico específico por su ID.

Verifica que el tópico exista antes de intentar eliminarlo.

Pruebas

Se realizaron pruebas exhaustivas de la API utilizando Postman para garantizar su correcto funcionamiento.

Todas las funcionalidades fueron verificadas, incluyendo validaciones y respuestas esperadas.

Tecnologías Utilizadas

Backend: Spring Boot

Base de Datos: PostgreSQL

Herramientas de Prueba: Postman