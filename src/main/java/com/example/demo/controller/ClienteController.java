package com.example.demo.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.demo.repo.modelo.Reserva;
import com.example.demo.repo.modelo.Vehiculo;
import com.example.demo.service.IClienteService;
import com.example.demo.service.ICobroService;
import com.example.demo.service.IReservaService;
import com.example.demo.service.IVehiculoService;
import com.example.demo.service.TO.ClienteTO;
import com.example.demo.service.TO.DatosReservaTO;
import com.example.demo.service.TO.PreReserva;
import com.example.demo.service.TO.ReservaTO;
import com.example.demo.service.TO.VehiculoTO;

@RestController // Servicio
@RequestMapping(path = "/clientes")
@CrossOrigin // (value="http://localhost:8080")
public class ClienteController {

	@Autowired
	private IClienteService iClienteService;

	@Autowired
	private IVehiculoService vehiculoService;

	@Autowired
	private IReservaService reservaService;

	@Autowired
	private ICobroService cobroService;


	// Buscar todos los clientes
	// http://localhost:8082/API/v1.0/Renta/clientes
	@GetMapping
	public ResponseEntity<List<ClienteTO>> buscarTodos() {
		List<ClienteTO> lista = this.iClienteService.buscarTodos();
		HttpHeaders cabeceras = new HttpHeaders();
		cabeceras.add("mensaje_242", "Lista consultada de manera satisfactoria.");
		cabeceras.add("mensaje_info", "El sistema va estar en mantenimiento el fin de semana.");
		return new ResponseEntity<>(lista, cabeceras, 242); // todo lo que no es de ka data principal va en al cabecera
	}

	// Buscar Clientes desde el cliente.
	// http://localhost:8082/API/v1.0/Renta/clientes
	@GetMapping(path = "/{cedula}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ClienteTO> buscarClienteCedula(@PathVariable String cedula) {
		System.out.println(cedula);
		ClienteTO cliente = this.iClienteService.buscarPorCedula(cedula);
		System.out.println(cedula);
		System.out.println(cliente);
		return ResponseEntity.status(HttpStatus.OK).body(cliente);
	}

	// Buscar reserva por numero
	// http://localhost:8082/API/v1.0/Renta/clientes/numero/reservas
	@GetMapping(path = "/{numero}/reservas")
	public ResponseEntity<List<ReservaTO>> consultarReservasPorNumero(@PathVariable Integer numero) {
		List<ReservaTO> lista = this.reservaService.buscarReservasPorIdCliente(numero);
		return ResponseEntity.status(HttpStatus.OK).body(lista);
	}

	// 1.b: RESERVAR VEHICULO
	// http://localhost:8082/API/v1.0/Renta/clientes/generarReserva
// 1.b: RESERVAR VEHICULO
// http://localhost:8082/API/v1.0/Renta/clientes/generarReserva
@PostMapping(path = "/generarReserva", consumes = MediaType.APPLICATION_JSON_VALUE)
public ResponseEntity<ReservaTO> reservarAuto(@RequestBody DatosReservaTO datoReserva) {

    Reserva nuevaReserva = this.reservaService.reservarVehiculo(datoReserva.getPlaca(), datoReserva.getCedula(),
            datoReserva.getFechaInicio(), datoReserva.getFechaFin());

    BigDecimal precio = this.vehiculoService.buscarPorPlaca(datoReserva.getPlaca()).getRenta();

    System.out.println(precio);
    this.cobroService.realizarPago(datoReserva.getTarjeta(), precio, nuevaReserva);

    var lista = this.reservaService.buscarReserva(datoReserva.getPlaca());
    var elem = lista.get(0);

    return ResponseEntity.status(HttpStatus.OK).body(elem);
}

	// Buscar Reservas por Cedula desde el cliente. //Falta cambiar a RESERVA TO
	// http://localhost:8082/API/v1.0/Renta/clientes/reservas/{placa}
	@GetMapping(path = "/reservas/{placa}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ReservaTO> buscarReservasPorPlaca(@PathVariable String placa) {
		System.out.println(placa);
		var lista = this.reservaService.buscarReserva(placa);
		var elem = lista.get(0);
		return ResponseEntity.status(HttpStatus.OK).body(elem);
	}

	// 1.c REGISTRARSE COMO CLLENTE
	// http://localhost:8082/API/v1.0/Renta/clientes
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public ResponseEntity<Integer> guardar(@RequestBody ClienteTO cliente) {
    System.out.println("ClienteTO: " + cliente);
    try {
        boolean registroExitoso = this.iClienteService.registro(cliente);
        if (registroExitoso) {
            return ResponseEntity.status(HttpStatus.CREATED).body(1);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(0);
        }
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(0);
    }
}

	// 1.d ACTUALIZAR CLIENTE, A EXCEPCIÓN DE LA CÉDULA
	// http://localhost:8082/API/v1.0/Renta/clientes/id
	@PatchMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void actualizarParcial(@RequestBody ClienteTO cliente, @PathVariable Integer id) {
		this.iClienteService.actualizarParcial(cliente.getNombre(), cliente.getApellido(), cliente.getFechaNacimiento(),
				cliente.getGenero(), cliente.getRegistro(), id);
	}

	// ELIMINCAR CLIENTE
	@DeleteMapping(path = "/{id}")
	public void borrar(@PathVariable Integer id) {
		this.iClienteService.eliminar(id);
	}

	// BUSCAR VEHICULOS
	@GetMapping("/buscarAutos")
	public String buscarAutosDisponibles(Model modelo, Vehiculo vehiculo) {

		List<Vehiculo> listaVehiculos = this.vehiculoService.vehiculosDisponibles(vehiculo.getMarca(),
				vehiculo.getModelo());
		modelo.addAttribute("vehiculos", listaVehiculos);
		return "vistaAutosDisponibles";
	}

	// Cotizar valor renta
	// http://localhost:8082/API/v1.0/Renta/clientes/cotizar
	@PostMapping(path = "cotizar")
	public ResponseEntity<Double> cotizarPorPlaca(@RequestBody PreReserva pr) {
		System.out.println(pr);
		VehiculoTO vehiculo = this.vehiculoService.buscarPorPlaca(pr.getPlaca());
		long numeroDeDias = ChronoUnit.DAYS.between(pr.getFechaInicio(), pr.getFechaFin())+1;

		BigDecimal valorPorDia = vehiculo.getRenta();
		BigDecimal rentaDias = valorPorDia.multiply(new BigDecimal(numeroDeDias));
		BigDecimal precioIva = rentaDias.multiply(new BigDecimal(0.12));
		BigDecimal precioTotal = rentaDias.add(precioIva);

		return ResponseEntity.status(HttpStatus.OK).body(precioTotal.doubleValue());
	}

	// http://localhost:8082/API/v1.0/Renta/clientes/fechas/
	@GetMapping(path = "/fechas/{placa}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<LocalDate>> fechasRentasPorVehiculo(@PathVariable String placa) {
		List<LocalDate> fechas = this.reservaService.obtenerFechasInicioFin(placa);

		return ResponseEntity.status(HttpStatus.OK).body(fechas);
	}

}
