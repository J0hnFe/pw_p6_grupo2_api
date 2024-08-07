package com.example.demo.repo.modelo;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(generator = "clie_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "clie_id_seq", sequenceName = "clie_id_seq", allocationSize = 1)
    @Column(name = "clie_id")
    private Integer id;

    @Column(name = "clie_nombre")
    private String nombre;

    @Column(name = "clie_apellido")
    private String apellido;

    @Column(name = "clie_numero_cedula")
    private String numeroCedula;

    @Column(name = "clie_fecha_nacimiento")
    private LocalDate fechaNacimiento;

    @Column(name = "clie_genero")
    private String genero;

    @Column(name = "clie_registro")
    private String registro;


    // Relacion uno a muchos con reserva
    @OneToMany(mappedBy = "cliente")
    private List<Reserva> reservas;

    // Getters y Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getNumeroCedula() {
        return numeroCedula;
    }

    public void setNumeroCedula(String numeroCedula) {
        this.numeroCedula = numeroCedula;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getRegistro() {
        return registro;
    }

    public void setRegistro(String registro) {
        this.registro = registro;
    }

   

    public List<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }

    // toString
    @Override
    public String toString() {
        return "Cliente [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", numeroCedula=" + numeroCedula
                + ", fechaNacimiento=" + fechaNacimiento + ", genero=" + genero + ", registro=" + registro +  "]";
    }

}
