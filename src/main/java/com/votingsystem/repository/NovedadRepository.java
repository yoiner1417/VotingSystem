package com.votingsystem.repository;

import com.votingsystem.model.Novedad;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NovedadRepository extends JpaRepository<Novedad, Long> {
    List<Novedad> findAllByOrderByFechaPublicacionDesc();
}

