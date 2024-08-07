package com.example.demo.repo.modelo.DTO;

import java.math.BigDecimal;
import java.io.Serializable;

import org.springframework.hateoas.RepresentationModel;

public class VehiculoDTO extends RepresentationModel<VehiculoDTO> implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 2815708059206892412L;
	
	// Vehiculo
    private String placa;
    private String modelo;
    private String marca;
    private String anio;
    private String estado;
    private BigDecimal renta;

    // Constructor vacio buena practica si se tiene un constructor con parametros
    public VehiculoDTO() {
    }

    public VehiculoDTO(String placa, String modelo, String marca, String anio, String estado, BigDecimal renta) {
        super();
        this.placa = placa;
        this.modelo = modelo;
        this.marca = marca;
        this.anio = anio;
        this.estado = estado;
        this.renta = renta;
    }

    // Getters y Setters
    public String getPlaca() {
        return placa;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
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

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public BigDecimal getRenta() {
        return renta;
    }

    public void setRenta(BigDecimal renta) {
        this.renta = renta;
    }

    // toString
    @SuppressWarnings("null")
    @Override
    public String toString() {
        return "VehiculoDTO [placa=" + placa + ", modelo=" + modelo + ", marca=" + marca + ", estado=" + estado
                + ", renta=" + renta + "]";
    }

}
