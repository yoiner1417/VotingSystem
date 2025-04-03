package com.votingsystem.repository;

import com.votingsystem.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository extends JpaRepository<Vote, Long> {
    boolean existsByUserId(Long userId);
    int countByCandidateId(Long candidateId);
    int countByActivityId(Long activityId);
}

