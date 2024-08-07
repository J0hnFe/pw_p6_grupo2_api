package com.example.demo.service;

import java.math.BigDecimal;

import com.example.demo.repo.modelo.Reserva;

public interface ICobroService {
    public void realizarPago(String numeroTarjeta, BigDecimal costo, Reserva reserva);
}
