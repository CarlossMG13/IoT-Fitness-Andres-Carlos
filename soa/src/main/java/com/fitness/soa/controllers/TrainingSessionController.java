package com.fitness.soa.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fitness.soa.models.TrainingSessionModel;
import com.fitness.soa.services.TrainingSessionService;

@RestController
@RequestMapping("/api/v1/sessions")
public class TrainingSessionController {
    
    @Autowired
    private TrainingSessionService sessionService;

    // POST: Crear una nueva sesion
    @PostMapping()
    public ResponseEntity<TrainingSessionModel> saveSession(@RequestBody TrainingSessionModel session) {
        TrainingSessionModel newSession = sessionService.saveSession(session);
        return new ResponseEntity<>(newSession, HttpStatus.CREATED);
    }

    // GET: Obtener todas las sesiones
    @GetMapping()
    public List<TrainingSessionModel> getAllSessions() {
        return sessionService.getAllSessions();
    }
    
    // GET: Obtener una sesión por ID
    @GetMapping("/{id}")
    public ResponseEntity<TrainingSessionModel> getSessionById(@PathVariable Long id) {
        Optional<TrainingSessionModel> session = sessionService.getSessionById(id);
        return session.map(ResponseEntity::ok)
                      .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // GET: Obtener sesiones por ID de Usuario
    @GetMapping("/user/{userId}")
    public List<TrainingSessionModel> getSessionsByUserId(@PathVariable Long userId) {
        return sessionService.getSessionsByUserId(userId);
    }

    // PUT: Actualizar una sesión existente
    @PutMapping("/{id}")
    public ResponseEntity<TrainingSessionModel> updateSession(@PathVariable Long id, @RequestBody TrainingSessionModel sessionDetails) {
        return sessionService.getSessionById(id)
                .map(session -> {
                    // Actualiza los campos necesarios
                    session.setTipoActividad(sessionDetails.getTipoActividad());
                    session.setDuracionMinutos(sessionDetails.getDuracionMinutos());
                    session.setCaloriasQuemadas(sessionDetails.getCaloriasQuemadas());
                    session.setDistanciaKm(sessionDetails.getDistanciaKm());
                    session.setFechaInicio(sessionDetails.getFechaInicio());
                    
                    TrainingSessionModel updatedSession = sessionService.saveSession(session);
                    return ResponseEntity.ok(updatedSession);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // DELETE: Eliminar una sesión
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSession(@PathVariable Long id) {
        if (sessionService.deleteSession(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
