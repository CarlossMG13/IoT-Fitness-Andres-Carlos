package com.fitness.soa.models;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table (name = "USUARIOS")
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column (name = "apellido", nullable = false, length = 100)
    private String apellido;

    @Column (name = "email", unique = true, nullable = false, length = 150)
    private String email;

    @Column (name = "fecha_nacimiento")
    private LocalDate fecha_nacimiento;

    @Column (name = "genero", length = 1, nullable = false)
    private Character genero; // H/M/O

    public Long getId_user() {
        return id;
    }

    public void setId_user(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(LocalDate fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public Character getGenero() {
        return genero;
    }

    public void setGenero(Character genero) {
        this.genero = genero;
    }

    // Constructor Vacio
    public UserModel() {

    }

    // Constructor completo
    public UserModel(Long id, String nombre, String apellido, String email, LocalDate fecha_nacimiento, Character genero){
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.fecha_nacimiento = fecha_nacimiento;
        this.genero = genero;
    }
}
