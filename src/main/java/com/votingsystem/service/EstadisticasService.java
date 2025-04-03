package com.votingsystem.service;

import com.votingsystem.repository.VoteRepository;
import com.votingsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstadisticasService {

    @Autowired
    private VoteRepository voteRepository;

    @Autowired
    private UserRepository userRepository;

    public long getTotalVotos() {
        return voteRepository.count();
    }

    public long getTotalUsuariosRegistrados() {
        return userRepository.count();
    }

    public double getPorcentajeParticipacion() {
        long totalUsuarios = getTotalUsuariosRegistrados();
        if (totalUsuarios == 0) {
            return 0;
        }
        return (double) getTotalVotos() / totalUsuarios * 100;
    }
}

