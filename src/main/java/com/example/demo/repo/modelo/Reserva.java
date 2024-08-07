package com.example.demo.repo.modelo;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "reserva")
public class Reserva {

    @Id
    @GeneratedValue(generator = "rese_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "rese_id_seq", sequenceName = "rese_id_seq", allocationSize = 1)
    @Column(name = "rese_id")
    private Integer id;

    @Column(name = "rese_numero_reserva")
    private String numeroReserva;

    @Column(name = "rese_numero_cedula")
    private String numero;

    @Column(name = "rese_dias")
    private Integer diasReserva;

    @Column(name = "rese_fecha_inicio")
    private LocalDate fechaInicio;

    @Column(name = "rese_fecha_fin")
    private LocalDate fechaFin;

    @Column(name = "rese_estado")
    private String estado;

    // Relacion muchos a uno con cliente
    @ManyToOne
    @JoinColumn(name = "rese_clie_id")
    private Cliente cliente;

    // Relacion muchos a uno con vehiculo
    @ManyToOne
    @JoinColumn(name = "rese_vehi_id")
    private Vehiculo vehiculo;

    // Relacion uno a uno con cobro
    @OneToOne(mappedBy = "reserva", fetch = FetchType.EAGER)
    private Cobro cobro;

    // Getters y Setters
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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public Cobro getCobro() {
        return cobro;
    }

    public void setCobro(Cobro cobro) {
        this.cobro = cobro;
    }

    // toString
    @Override
    public String toString() {
        return "Reserva [id=" + id + ", numeroReserva=" + numeroReserva + ", numero=" + numero + ", diasReserva="
                + diasReserva + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + ", estado=" + estado
                + ", cliente=" + cliente + ", vehiculo=" + vehiculo + ", cobro=" + cobro + "]";
    }

}
