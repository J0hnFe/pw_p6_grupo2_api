package com.example.demo.service.TO;

import java.io.Serializable;
import java.time.LocalDate;

public class DatosReservaTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3752560293744568264L;
	private String placa;
	private String cedula;
	private String tarjeta;
	private LocalDate fechaInicio;
	private LocalDate fechaFin;

	public DatosReservaTO() {
	}

	public DatosReservaTO(String placa, String cedula, String tarjeta, LocalDate fechaInicio, LocalDate fechaFin) {
		super();
		this.placa = placa;
		this.cedula = cedula;
		this.tarjeta = tarjeta;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
	}

	// Getters and Setters
	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
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

	public String getTarjeta() {
		return tarjeta;
	}

	public void setTarjeta(String tarjeta) {
		this.tarjeta = tarjeta;
	}
}
