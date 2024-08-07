package com.example.demo.repo.modelo;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Repository;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "vehiculo")
public class Vehiculo {

    @Id
    @GeneratedValue(generator = "vehi_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "vehi_id_seq", sequenceName = "vehi_id_seq", allocationSize = 1)
    @Column(name = "vehi_id")
    private Integer id;

    @Column(name = "vehi_placa")
    private String placa;

    @Column(name = "vehi_modelo")
    private String modelo;

    @Column(name = "vehi_marca")
    private String marca;

    @Column(name = "vehi_anio_fabricacion")
    private String anioFabricacion;

    @Column(name = "vehi_estado")
    private String estado;

    @Column(name = "vehi_pais_fabricacion")
    private String paisFabricacion;

    @Column(name = "vehi_cilindraje")
    private String cilindraje;

    @Column(name = "vehi_avaluo")
    private BigDecimal avaluo;

    @Column(name = "vehi_renta")
    private BigDecimal renta;

    // Relacion uno a muchos con renta
    @OneToMany(mappedBy = "vehiculo")
    private List<Reserva> reservas;

    // Getters y Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getAnioFabricacion() {
        return anioFabricacion;
    }

    public void setAnioFabricacion(String anioFabricacion) {
        this.anioFabricacion = anioFabricacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getPaisFabricacion() {
        return paisFabricacion;
    }

    public void setPaisFabricacion(String paisFabricacion) {
        this.paisFabricacion = paisFabricacion;
    }

    public String getCilindraje() {
        return cilindraje;
    }

    public void setCilindraje(String cilindraje) {
        this.cilindraje = cilindraje;
    }

    public BigDecimal getAvaluo() {
        return avaluo;
    }

    public void setAvaluo(BigDecimal avaluo) {
        this.avaluo = avaluo;
    }

    public BigDecimal getRenta() {
        return renta;
    }

    public void setRenta(BigDecimal renta) {
        this.renta = renta;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }

    // To String
    @Override
    public String toString() {
        return "Vehiculo [id=" + id + ", placa=" + placa + ", modelo=" + modelo + ", marca=" + marca
                + ", anioFabricacion=" + anioFabricacion + ", estado=" + estado + ", paisFabricacion=" + paisFabricacion
                + ", cilindraje=" + cilindraje + ", avaluo=" + avaluo + ", renta=" + renta + "]";
    }

}
