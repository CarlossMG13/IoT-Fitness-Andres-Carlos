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
@Table (name = "SESIONES_ENTRENAMIENTO")
public class TrainingSessionModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relacion muchos a uno (ManyToOne) con UserModel
    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private UserModel user;

    @Column (name = "tipo_actividad", nullable = false, length = 100)
    private String tipoActividad; // Correr, Ciclismo, Pesas, Yoga

    @Column (name = "duracion_minutos", nullable = false)
    private Integer duracionMinutos; // Duración en minutos

    @Column (name = "calorias_quemadas")
    private Double caloriasQuemadas; // Estimación de calorías

    @Column (name = "distancia_km")
    private Double distancia; // Distancia recorrida (si aplica)

    @Column (name = "fecha_inicio", nullable = false)
    private LocalDateTime fechaInicio;

    /* Getters and Setters */

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

    public String getTipoActividad() {
        return tipoActividad;
    }

    public void setTipoActividad(String tipoActividad) {
        this.tipoActividad = tipoActividad;
    }

    public Integer getDuracionMinutos() {
        return duracionMinutos;
    }

    public void setDuracionMinutos(Integer duracionMinutos) {
        this.duracionMinutos = duracionMinutos;
    }

    public Double getCaloriasQuemadas() {
        return caloriasQuemadas;
    }

    public void setCaloriasQuemadas(Double caloriasQuemadas) {
        this.caloriasQuemadas = caloriasQuemadas;
    }

    public Double getDistanciaKm() {
        return distancia;
    }

    public void setDistanciaKm(Double distancia) {
        this.distancia = distancia;
    }

    public LocalDateTime getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDateTime fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    /* Constructores */

    public TrainingSessionModel() {
        // Constructor Vacio
    }

    public TrainingSessionModel(Long id, UserModel user, String tipoActividad, Integer duracionMinutos, Double caloriasQuemadas, Double distancia, LocalDateTime fechaInicio) {
        this.id = id;
        this.user = user;
        this.tipoActividad = tipoActividad;
        this.duracionMinutos = duracionMinutos;
        this.caloriasQuemadas = caloriasQuemadas;
        this.distancia = distancia;
        this.fechaInicio = fechaInicio;
    }

}
