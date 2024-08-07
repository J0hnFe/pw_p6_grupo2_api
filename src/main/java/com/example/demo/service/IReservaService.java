package com.example.demo.service;

import java.time.LocalDate;
import java.util.List;

import com.example.demo.repo.modelo.Reserva;
import com.example.demo.repo.modelo.DTO.ReservaDTO;
import com.example.demo.service.TO.ReporteTO;
import com.example.demo.service.TO.ReservaTO;

public interface IReservaService {
    public Reserva reservarVehiculo(String placa, String cedula, LocalDate inicio, LocalDate fin);

    public boolean buscarvehiculoDisponible(String placa, LocalDate inicio, LocalDate fin);

    public void retiro(String numero);

    public ReservaDTO buscarAutoReserva(String numeroReserva);

    public List<ReservaTO> buscarReservasPorIdCliente(Integer id);

    public List<ReporteTO> Reporte(LocalDate fechaInicio, LocalDate fechaFin);

    public void retirarVehiculo(String numeroReserva);

    public List<ReporteTO> reporteReservas(LocalDate fechaInicio, LocalDate fechaFin);

    public List<ReservaTO> buscarReserva(String placa);

    public List<LocalDate> obtenerFechasInicioFin(String placaVehiculo);
}
