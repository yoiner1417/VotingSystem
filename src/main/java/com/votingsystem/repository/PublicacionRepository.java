package com.votingsystem.repository;

import com.votingsystem.model.Publicacion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublicacionRepository extends JpaRepository<Publicacion, Long> {
    Page<Publicacion> findAllByOrderByFechaPublicacionDesc(Pageable pageable);
}

