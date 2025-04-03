package com.votingsystem.service;

import com.votingsystem.model.Publicacion;
import com.votingsystem.repository.PublicacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PublicacionService {

    @Autowired
    private PublicacionRepository publicacionRepository;

    public Page<Publicacion> obtenerTodasLasPublicaciones(Pageable pageable) {
        return publicacionRepository.findAllByOrderByFechaPublicacionDesc(pageable);
    }

    public Publicacion guardarPublicacion(Publicacion publicacion) {
        return publicacionRepository.save(publicacion);
    }

    public Publicacion obtenerPublicacionPorId(Long id) {
        return publicacionRepository.findById(id).orElse(null);
    }
}

