package com.fitness.soa.models;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table (name = "REGISTROS_FRECUENCIA_CARDIACA")
public class HeartRateRecordModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relación Muchos a Uno (ManyToOne) con UserModel
    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private UserModel user;

    @Column (name = "fecha_hora", nullable = false)
    private LocalDateTime fechaHora; // Momento de la medición

    @Column (name = "bpm", nullable = false)
    private Integer bpm; // Latidos por minuto

    // --- Getters y Setters ---

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public Integer getBpm() {
        return bpm;
    }

    public void setBpm(Integer bpm) {
        this.bpm = bpm;
    }

    // Constructor Vacío
    public HeartRateRecordModel() {
    }

    // Constructor Completo
    public HeartRateRecordModel(Long id, UserModel user, LocalDateTime fechaHora, Integer bpm) {
        this.id = id;
        this.user = user;
        this.fechaHora = fechaHora;
        this.bpm = bpm;
    }
}