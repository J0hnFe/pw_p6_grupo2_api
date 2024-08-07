package com.example.demo.service;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repo.IClienteRepo;
import com.example.demo.repo.IReservaRepo;
import com.example.demo.repo.IVehiculoRepo;
import com.example.demo.repo.modelo.Cliente;
import com.example.demo.repo.modelo.Reserva;
import com.example.demo.repo.modelo.Vehiculo;
import com.example.demo.repo.modelo.DTO.ReservaDTO;
import com.example.demo.service.TO.ReporteTO;
import com.example.demo.service.TO.ReservaTO;

@Service
public class ReservaServiceImpl implements IReservaService {

	@Autowired
	private IVehiculoRepo vehiculoRepository;

	@Autowired
	private IReservaRepo reservaRepository;

	@Autowired
	private IClienteRepo clienteRepository;

	@Override
	// @Transactional(value = TxType.REQUIRES_NEW)
	public Reserva reservarVehiculo(String placa, String cedula, LocalDate inicio, LocalDate fin) {
		Vehiculo vehiculo = this.vehiculoRepository.buscarPorPlaca(placa);
		Cliente cliente = this.clienteRepository.buscarCedula(cedula);

		LocalDate fInicio = inicio;
		LocalDate fFin = fin;

		int dias = Period.between(fInicio, fFin).getDays() + 1;
		System.out.println("\nDias de reserva: " + dias + "\n");

		Reserva reserva = new Reserva();

		int numero = (int) (Math.random() * 100 + 1);
		System.out.println("esfsefsef" + numero);
		String cadena = cedula.substring(0, 5);
		String codigoReserva = "R" + numero + "-" + cadena;

		vehiculo.setEstado("Disponible");
		this.vehiculoRepository.actualizarEstado(vehiculo);

		reserva.setNumeroReserva(codigoReserva);
		reserva.setDiasReserva(dias);
		reserva.setFechaInicio(inicio);
		reserva.setFechaFin(fin);
		reserva.setEstado("Reservado");
		reserva.setCliente(cliente);
		reserva.setVehiculo(vehiculo);
		reserva.setNumero(cedula);

		this.reservaRepository.guardar(reserva);
		System.out.println("El vehiculo ha sido reservado");
		return reserva;
	}

	@Override
	// @Transactional(value = TxType.REQUIRES_NEW)
	public boolean buscarvehiculoDisponible(String placa, LocalDate inicio, LocalDate fin) {
		List<Reserva> reservas = this.reservaRepository.buscarReserva(placa);
		LocalDate fInicio = inicio;
		LocalDate fFin = fin;

		if (reservas.size() == 0) {
			return true;
		} else {

			for (Reserva dato : reservas) {
				LocalDate fechaInicio = dato.getFechaInicio();
				LocalDate fechaFin = dato.getFechaFin();

				if (fechaInicio.compareTo(fInicio) > 0 && fechaInicio.compareTo(fFin) > 0
						|| fechaFin.compareTo(fInicio) < 0 && fechaFin.compareTo(fFin) < 0) {
				} else {
					System.out.println(
							"El auto ya esta reservado :" + dato.getFechaInicio() + " - " + dato.getFechaFin());
					return false;
				}
			}
			return true;
		}
	}

	@Override
	public List<ReservaTO> buscarReserva(String placa) {
		var lista = this.reservaRepository.buscarReserva(placa);
		List<ReservaTO> l = new ArrayList<>();
		for (Reserva reserva : lista) {
			l.add(convertir(reserva));
		}

		return l;
	}

	@Override
	// @Transactional(value = TxType.REQUIRES_NEW)
	public void retiro(String numero) {
		Reserva reserva = this.reservaRepository.buscarAutoReserva(numero);
		reserva.setEstado("Retirado");
		this.reservaRepository.actualizarReserva(reserva);

		Vehiculo vehiculo = this.vehiculoRepository.buscarPorPlaca(reserva.getVehiculo().getPlaca());
		vehiculo.setEstado("No Disponible");
		this.vehiculoRepository.actualizarEstado(vehiculo);
	}

	@Override
	// @Transactional(value = TxType.REQUIRES_NEW)
	public ReservaDTO buscarAutoReserva(String numero) {

		Reserva reserva = this.reservaRepository.buscarAutoReserva(numero);
		Vehiculo vehiculo = this.vehiculoRepository.buscarPorPlaca(reserva.getVehiculo().getPlaca());

		ReservaDTO reservadto = new ReservaDTO();
		reservadto.setPlaca(vehiculo.getPlaca());
		reservadto.setEstado(vehiculo.getEstado());
		reservadto.setModelo(vehiculo.getModelo());
		reservadto.setFecha(reserva.getFechaInicio() + " hasta " + reserva.getFechaFin());
		reservadto.setReservadoPor(reserva.getNumero());

		return reservadto;

	}

	// BUSCAR RESERVAS POR ID DEL CLIENTE
	@Override
	// @Transactional(value = TxType.REQUIRES_NEW)
	public List<ReservaTO> buscarReservasPorIdCliente(Integer id) {
		List<Reserva> lista = this.reservaRepository.seleccionarPorIdCiente(id);
		List<ReservaTO> listaFinal = new ArrayList<>();

		for (Reserva res : lista) {
			listaFinal.add(this.convertir(res));
		}
		return listaFinal;
	}

	private ReservaTO convertir(Reserva reserva) {
		ReservaTO reservaTO = new ReservaTO();

		reservaTO.setId(reserva.getId());
		reservaTO.setDiasReserva(reserva.getDiasReserva());
		reservaTO.setEstado(reserva.getEstado());
		reservaTO.setFechaInicio(reserva.getFechaInicio());
		reservaTO.setFechaFin(reserva.getFechaFin());
		reservaTO.setNumero(reserva.getNumero());
		reservaTO.setNumeroReserva(reserva.getNumeroReserva());

		return reservaTO;
	}

	@Override
	public List<ReporteTO> Reporte(LocalDate fechaInicio, LocalDate fechaFin) {
		List<Reserva> lista = this.reservaRepository.buscarPorFechas(fechaInicio, fechaFin);
		List<ReporteTO> listaFinal = new ArrayList<>();
		ReporteTO reporteTO = new ReporteTO();

		for (Reserva res : lista) {

			Cliente cliente = this.clienteRepository.buscarPorId(res.getCliente().getId());
			Vehiculo vehiculo = this.vehiculoRepository.buscarPorPlaca(res.getVehiculo().getPlaca());
			reporteTO.setNumeroReserva(res.getNumero());
			reporteTO.setDiasReserva(res.getDiasReserva());
			reporteTO.setFechaInicio(res.getFechaInicio());
			reporteTO.setFechaFin(res.getFechaFin());
			reporteTO.setEstado(res.getEstado());
			reporteTO.setApellido(cliente.getApellido());
			reporteTO.setNumeroCedula(cliente.getNumeroCedula());
			reporteTO.setPlaca(vehiculo.getPlaca());
			reporteTO.setModelo(vehiculo.getModelo());
			reporteTO.setMarca(vehiculo.getMarca());
			listaFinal.add(reporteTO);
		}

		return listaFinal;
	}

	@Override
	public void retirarVehiculo(String numeroReserva) {
		// TODO Auto-generated method stub
		// Funcionalidad que permitirá retirar un vehículo previamente reservado; el
		// sistema permitirá ingresar un número de reserva
		// y cambiará el estado de la reserva a "ejecutada" y el estado del vehículo a
		// "No Disponible"
		Reserva reserva = this.reservaRepository.buscarAutoReserva(numeroReserva);
		reserva.setEstado("Ejecutada");
		this.reservaRepository.actualizarReserva(reserva);
		Vehiculo vehiculo = this.vehiculoRepository.buscarPorPlaca(reserva.getVehiculo().getPlaca());
		vehiculo.setEstado("No Disponible");
		this.vehiculoRepository.actualizarEstado(vehiculo);
	}

	@Override
	public List<ReporteTO> reporteReservas(LocalDate fechaInicio, LocalDate fechaFin) {
		List<ReporteTO> lista = this.reservaRepository.seleccionarListaPorFechas(fechaInicio, fechaFin);
		return lista;
	}

	@Override
	public List<LocalDate> obtenerFechasInicioFin(String placaVehiculo) {
		List<Reserva> reservas = this.reservaRepository.seleccionarReservasPorVehiculo(placaVehiculo);

		List<List<LocalDate>> fechasInicioFinConDiasFaltantes = reservas.stream()
				.map(reserva -> {
					LocalDate fechaInicio = reserva.getFechaInicio();
					LocalDate fechaFin = reserva.getFechaFin();
					long diasEntre = ChronoUnit.DAYS.between(fechaInicio, fechaFin);

					List<LocalDate> listaFechas = new ArrayList<>();
					listaFechas.add(fechaInicio);

					for (int i = 1; i <= diasEntre; i++) {
						listaFechas.add(fechaInicio.plusDays(i));
					}

					listaFechas.add(fechaFin);

					return listaFechas;
				})
				.collect(Collectors.toList());

		List<LocalDate> todasLasFechas = new ArrayList<>();

		// Iterar sobre la lista de listas
		for (List<LocalDate> lista : fechasInicioFinConDiasFaltantes) {
			// Agregar todas las fechas de la lista interna a la lista resultante
			todasLasFechas.addAll(lista);
		}
		return todasLasFechas;
	}

}
