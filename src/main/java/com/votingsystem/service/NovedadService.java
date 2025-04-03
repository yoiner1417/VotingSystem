package com.votingsystem.service;

import com.votingsystem.model.Novedad;
import com.votingsystem.repository.NovedadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NovedadService {

    @Autowired
    private NovedadRepository novedadRepository;

    public List<Novedad> findAll() {
        return novedadRepository.findAllByOrderByFechaPublicacionDesc();
    }

    public Novedad save(Novedad novedad) {
        return novedadRepository.save(novedad);
    }

    public void delete(Long id) {
        novedadRepository.deleteById(id);
    }

    public Novedad findById(Long id) {
        return novedadRepository.findById(id).orElse(null);
    }

    public void update(Novedad novedad) {
        novedadRepository.save(novedad);
    }
}

