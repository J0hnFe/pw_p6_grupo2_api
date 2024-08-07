package com.example.demo.repo;

import java.time.LocalDate;
import java.util.List;

import com.example.demo.repo.modelo.Reserva;
import com.example.demo.service.TO.ReporteTO;

public interface IReservaRepo {
    public void guardar(Reserva r);

    public void actualizarReserva(Reserva r);

    public List<Reserva> buscarReserva(String placa);

    public Reserva buscarAutoReserva(String numeroReserva);

    public Reserva buscarPlaca(Integer id, String placa);

    public Reserva buscarReservaPlaca(String placa);

    public List<Reserva> buscarPorFechas(LocalDate fechaInicio, LocalDate fechaFin);

    public List<Reserva> seleccionarPorIdCiente(Integer id);

    public List<ReporteTO> seleccionarListaPorFechas(LocalDate fechaInicio, LocalDate fechaFin);

    public List<Reserva> seleccionarReservasPorVehiculo(String placaVehiculo);
}
