package com.votingsystem.service;

import com.votingsystem.model.Candidate;
import com.votingsystem.repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CandidateService {

    @Autowired
    private CandidateRepository candidateRepository;

    public List<Candidate> findAll() {
        return candidateRepository.findAll();
    }

    public Page<Candidate> findAllPaginated(Pageable pageable) {
        return candidateRepository.findAll(pageable);
    }

    public Candidate findById(Long id) {
        return candidateRepository.findById(id).orElse(null);
    }

    public Candidate save(Candidate candidate) {
        return candidateRepository.save(candidate);
    }

    public void deleteById(Long id) {
        candidateRepository.deleteById(id);
    }
}

