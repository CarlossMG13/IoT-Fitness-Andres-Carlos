package com.fitness.soa.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fitness.soa.models.HeartRateRecordModel;
import com.fitness.soa.repositories.IHeartRateRecordRepository;

@Service
public class HeartRateRecordService {

    @Autowired
    private IHeartRateRecordRepository heartRateRepository;

    // CRUD - Create / Update
    public HeartRateRecordModel saveRecord(HeartRateRecordModel record) {
        return heartRateRepository.save(record);
    }

    // CRUD - Read All
    public List<HeartRateRecordModel> getAllRecords() {
        return heartRateRepository.findAll();
    }

    // CRUD - Read By ID
    public Optional<HeartRateRecordModel> getRecordById(Long id) {
        return heartRateRepository.findById(id);
    }
    
    // Read - By User ID
    public List<HeartRateRecordModel> getRecordsByUserId(Long userId) {
        return heartRateRepository.findByUserId(userId);
    }

    // CRUD - Delete
    public boolean deleteRecord(Long id) {
        if (heartRateRepository.existsById(id)) {
            heartRateRepository.deleteById(id);
            return true;
        }
        return false;
    }
}