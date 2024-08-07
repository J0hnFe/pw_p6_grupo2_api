package com.example.demo.repo.modelo;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "cobro")
public class Cobro {

    @Id
    @Column(name = "cobr_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cobr_id_seq")
    @SequenceGenerator(name = "cobr_id_seq", sequenceName = "cobr_id_seq", allocationSize = 1)
    private Integer id;

    @Column(name = "cobr_tarjeta")
    private String tarjeta;

    @Column(name = "cobr_fecha_pago")
    private LocalDateTime fechaPago;

    @Column(name = "cobr_subtotal")
    private BigDecimal subTotal;

    @Column(name = "cobr_precio_iva")
    private BigDecimal precioIva;

    @Column(name = "cobr_precio_total")
    private BigDecimal precioTotal;

    // Relacion uno a uno con cobro
    @OneToOne
    @JoinColumn(name = "cobr_rese_id")
    private Reserva reserva;

    // Getters y Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTarjeta() {
        return tarjeta;
    }

    public void setTarjeta(String tarjeta) {
        this.tarjeta = tarjeta;
    }

    public LocalDateTime getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(LocalDateTime fechaPago) {
        this.fechaPago = fechaPago;
    }

    public BigDecimal getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
    }

    public BigDecimal getPrecioIva() {
        return precioIva;
    }

    public void setPrecioIva(BigDecimal precioIva) {
        this.precioIva = precioIva;
    }

    public BigDecimal getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(BigDecimal precioTotal) {
        this.precioTotal = precioTotal;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    // toString
    @Override
    public String toString() {
        return "Cobro [id=" + id + ", tarjeta=" + tarjeta + ", fechaPago=" + fechaPago + ", subTotal=" + subTotal
                + ", precioIva=" + precioIva + ", precioTotal=" + precioTotal + ", reserva=" + reserva + "]";
    }

}
