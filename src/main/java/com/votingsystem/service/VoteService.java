package com.votingsystem.service;

import com.votingsystem.model.Vote;
import com.votingsystem.model.Candidate;
import com.votingsystem.model.Activity;
import com.votingsystem.repository.VoteRepository;
import com.votingsystem.repository.CandidateRepository;
import com.votingsystem.repository.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class VoteService {

    @Autowired
    private VoteRepository voteRepository;

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private ActivityRepository activityRepository;

    private boolean votacionesAbiertas = false;

    public boolean hasUserVoted(Long userId) {
        return voteRepository.existsByUserId(userId);
    }

    public void registerVote(Long userId, Long candidateId, Long activityId) {
        if (!votacionesAbiertas) {
            throw new IllegalStateException("Las votaciones están cerradas");
        }
        Vote vote = new Vote();
        vote.setUserId(userId);
        vote.setCandidateId(candidateId);
        vote.setActivityId(activityId);
        voteRepository.save(vote);
    }

    public Map<String, Integer> obtenerResultadosCandidatos() {
        List<Candidate> candidates = candidateRepository.findAll();
        Map<String, Integer> results = new HashMap<>();
        for (Candidate candidate : candidates) {
            int votes = voteRepository.countByCandidateId(candidate.getId());
            results.put(candidate.getNombre(), votes);
        }
        return results;
    }

    public Map<String, Integer> obtenerResultadosActividades() {
        List<Activity> activities = activityRepository.findAll();
        Map<String, Integer> results = new HashMap<>();
        for (Activity activity : activities) {
            int votes = voteRepository.countByActivityId(activity.getId());
            results.put(activity.getNombre(), votes);
        }
        return results;
    }

    public void abrirVotaciones() {
        votacionesAbiertas = true;
    }

    public void cerrarVotaciones() {
        votacionesAbiertas = false;
    }

    public boolean estanVotacionesAbiertas() {
        return votacionesAbiertas;
    }

    public Object getEstadoVotaciones() {
        return null;
    }

    public void setEstadoVotaciones(String estado) {
    }
}

