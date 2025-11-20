package com.fitness.soa.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fitness.soa.models.HeartRateRecordModel;

@Repository
public interface IHeartRateRecordRepository extends JpaRepository<HeartRateRecordModel, Long> {

    /**
     * Encuentra todos los registros de frecuencia cardiaca de un usuario específico
     * usando el ID del usuario.
     */
    List<HeartRateRecordModel> findByUserId(Long userId);
}
