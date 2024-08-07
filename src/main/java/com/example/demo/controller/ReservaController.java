package com.example.demo.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.repo.modelo.DTO.ReservaDTO;
import com.example.demo.service.IReservaService;
import com.example.demo.service.TO.ReporteTO;

@RestController
@RequestMapping(path = "/reservas") // http://localhost:8080/reservas
@CrossOrigin // "http://localhost:4200
public class ReservaController {

    @Autowired
    private IReservaService iReservaService;

    @PutMapping(path = "/retiro")
    public void retiroReservado(@RequestParam String numeroReserva) {
        System.out.println("sefsen" + numeroReserva);
        this.iReservaService.retirarVehiculo(numeroReserva);
    }

    @GetMapping(path = "/{numeroReserva}", produces = "application/json")
    public ResponseEntity<ReservaDTO> buscarReserva(@PathVariable String numeroReserva) {
        ReservaDTO reserva = this.iReservaService.buscarAutoReserva(numeroReserva);

        System.out.println("rrrr" + reserva);
        return ResponseEntity.status(HttpStatus.OK).body(reserva);
        // http://localhost:8080/API/v1.0/Matricula/estudiantes/{id} GET
    }

    @GetMapping(path = "/{fechaInicio}/{fechaFin}", produces = "application/json")
    public ResponseEntity<List<ReporteTO>> reporteReservas(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaInicio,
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFin) {
        List<ReporteTO> lista = this.iReservaService.reporteReservas(fechaInicio, fechaFin);
        return ResponseEntity.status(HttpStatus.OK).body(lista);
    }
}
