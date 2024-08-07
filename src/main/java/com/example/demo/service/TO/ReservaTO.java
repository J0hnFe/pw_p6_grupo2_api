package com.example.demo.service.TO;

import java.io.Serializable;
import java.time.LocalDate;

import org.springframework.hateoas.RepresentationModel;

public class ReservaTO extends RepresentationModel<ReservaTO> implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -987159569716004360L;

	private Integer id;

	private String numeroReserva;

	private String numero;

	private Integer diasReserva;

	private LocalDate fechaInicio;

	private LocalDate fechaFin;

	private String estado;

	// Getters and Setters
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNumeroReserva() {
		return numeroReserva;
	}

	public void setNumeroReserva(String numeroReserva) {
		this.numeroReserva = numeroReserva;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Integer getDiasReserva() {
		return diasReserva;
	}

	public void setDiasReserva(Integer diasReserva) {
		this.diasReserva = diasReserva;
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

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

}
