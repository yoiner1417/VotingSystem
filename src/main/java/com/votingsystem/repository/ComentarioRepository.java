package com.votingsystem.repository;

import com.votingsystem.model.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
    List<Comentario> findByPublicacionIdOrderByFechaComentarioDesc(Long publicacionId);
}

