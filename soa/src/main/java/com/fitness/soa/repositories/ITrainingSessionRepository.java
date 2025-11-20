package com.fitness.soa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fitness.soa.models.TrainingSessionModel;

@Repository
public interface ITrainingSessionRepository extends JpaRepository<TrainingSessionModel, Long> {
    // Obtener todas las sesiones de un usuario en especifico
    List<TrainingSessionModel> findByUserId(Long userId);
}
