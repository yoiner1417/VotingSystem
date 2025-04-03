package com.votingsystem.service;

import com.votingsystem.model.Activity;
import com.votingsystem.repository.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityService {

    @Autowired
    private ActivityRepository activityRepository;

    public List<Activity> findAll() {
        return activityRepository.findAll();
    }

    public Activity save(Activity activity) {
        return activityRepository.save(activity);
    }

    public Activity findById(Long actividadId) {
        return null;
    }

    public void deleteById(Long id) {
    }

    // Add more methods as needed
}

