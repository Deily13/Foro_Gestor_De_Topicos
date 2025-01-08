package com.example.Gestor.De.Topicos.repository;

import com.example.Gestor.De.Topicos.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {
}
