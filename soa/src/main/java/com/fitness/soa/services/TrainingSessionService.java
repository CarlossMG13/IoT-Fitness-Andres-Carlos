package com.fitness.soa.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fitness.soa.models.TrainingSessionModel;
import com.fitness.soa.repositories.ITrainingSessionRepository;

@Service
public class TrainingSessionService {
    
    @Autowired
    private ITrainingSessionRepository sessionRepository;

    // CRUD - Create
    public TrainingSessionModel saveSession(TrainingSessionModel session) {
        return sessionRepository.save(session);
    }

    // CRUD - Read All
    public List<TrainingSessionModel> getAllSessions() {
        return sessionRepository.findAll();
    }

    // CRUD - Read By ID
    public Optional<TrainingSessionModel> getSessionById(Long id) {
        return sessionRepository.findById(id);
    }
    
    // Read - By User ID
    public List<TrainingSessionModel> getSessionsByUserId(Long userId) {
        return sessionRepository.findByUserId(userId);
    }

    // CRUD - Delete
    public boolean deleteSession(Long id) {
        try {
            sessionRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
