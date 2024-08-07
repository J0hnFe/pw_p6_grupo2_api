package com.example.demo.service.TO;

import java.time.LocalDate;

public class PreReserva {
    private String placa;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;

    // Getters and Setters
    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    @Override
    public String toString() {
        return "PreReserva [placa=" + placa + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + "]";
    }
}
