package com.example.model;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;

public class Reserva {
    private Long id;

    @NotNull(message = "El usuario es obligatorio")
    private Long usuarioId;

    @NotNull(message = "El espacio es obligatorio")
    private Long espacioId;

    @NotNull(message = "La fecha es obligatoria")
    private LocalDate fecha;

    public Reserva() {}

    public Reserva(Long id, Long usuarioId, Long espacioId, LocalDate fecha) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.espacioId = espacioId;
        this.fecha = fecha;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getUsuarioId() { return usuarioId; }
    public void setUsuarioId(Long usuarioId) { this.usuarioId = usuarioId; }
    public Long getEspacioId() { return espacioId; }
    public void setEspacioId(Long espacioId) { this.espacioId = espacioId; }
    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }
}