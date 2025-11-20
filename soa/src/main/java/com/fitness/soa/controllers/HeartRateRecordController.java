package com.fitness.soa.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fitness.soa.models.HeartRateRecordModel;
import com.fitness.soa.services.HeartRateRecordService;

@RestController
@RequestMapping("/api/v1/heartrate")
public class HeartRateRecordController {

    @Autowired
    private HeartRateRecordService heartRateService;

    // POST: Crear un nuevo registro
    @PostMapping()
    public ResponseEntity<HeartRateRecordModel> saveRecord(@RequestBody HeartRateRecordModel record) {
        HeartRateRecordModel newRecord = heartRateService.saveRecord(record);
        return new ResponseEntity<>(newRecord, HttpStatus.CREATED);
    }

    // GET: Obtener todos los registros
    @GetMapping()
    public List<HeartRateRecordModel> getAllRecords() {
        return heartRateService.getAllRecords();
    }

    // GET: Obtener un registro por ID específico
    @GetMapping("/{id}")
    public ResponseEntity<HeartRateRecordModel> getRecordById(@PathVariable Long id) {
        Optional<HeartRateRecordModel> record = heartRateService.getRecordById(id);
        return record.map(ResponseEntity::ok)
                     .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // GET: Obtener todos los registros de frecuencia de un Usuario
    @GetMapping("/user/{userId}")
    public List<HeartRateRecordModel> getRecordsByUserId(@PathVariable Long userId) {
        return heartRateService.getRecordsByUserId(userId);
    }
    
    // PUT: Actualizar un registro existente
    @PutMapping("/{id}")
    public ResponseEntity<HeartRateRecordModel> updateRecord(@PathVariable Long id, @RequestBody HeartRateRecordModel recordDetails) {
        return heartRateService.getRecordById(id)
                .map(record -> {
                    // campos que se pueden cambiar
                    record.setFechaHora(recordDetails.getFechaHora());
                    record.setBpm(recordDetails.getBpm());
                    
                    HeartRateRecordModel updatedRecord = heartRateService.saveRecord(record);
                    return ResponseEntity.ok(updatedRecord);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // DELETE: Eliminar un registro
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecord(@PathVariable Long id) {
        if (heartRateService.deleteRecord(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
