package com.example.demo.repo.modelo.DTO;

import java.io.Serializable;

public class ReservaDTO implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -1105344722727052164L;
	private String placa;
    private String modelo;
    private String estado;
    private String fecha;
    private String reservadoPor;

    // Getters y Setters
    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getReservadoPor() {
        return reservadoPor;
    }

    public void setReservadoPor(String reservadoPor) {
        this.reservadoPor = reservadoPor;
    }

}
