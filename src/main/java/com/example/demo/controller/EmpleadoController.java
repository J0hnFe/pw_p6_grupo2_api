package com.example.demo.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

// Import estáticos
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.example.demo.repo.modelo.Vehiculo;
import com.example.demo.service.IClienteService;
import com.example.demo.service.IReservaService;
import com.example.demo.service.IVehiculoService;
import com.example.demo.service.TO.ClienteTO;
import com.example.demo.service.TO.VehiculoTO;

import jakarta.persistence.NoResultException;

@CrossOrigin
@RestController // Servicio
@RequestMapping("/empleados")
public class EmpleadoController {

	@Autowired
	private IClienteService iClienteService;
	@Autowired
	private IVehiculoService iVehiculoService;
	@Autowired
	private IReservaService reservaService;



	static List<String> genero = null;

	static {
		genero = new ArrayList<>();
		genero.add("F");
		genero.add("M");
	}

	// CLIENTES
	// 2.a: REGISTRAR CLIENTE
	// http://localhost:8082/API/v1.0/Renta/empleados
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public ResponseEntity<Integer> registroComoEmpleado(@RequestBody ClienteTO cliente) {
    System.out.println("ClienteTO: " + cliente);

    try {
        boolean validar = this.iClienteService.validarCliente(cliente);

        if (!validar) {
            // Si no están llenos, retornar un código de error
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(3);
        }

        // Verificar si ya existe un vehículo con la misma placa
        boolean clienteExistente = this.iClienteService.existeClienteConCedula(cliente.getNumeroCedula());

        if (clienteExistente) {
            // Ya existe un vehículo con la misma placa, no se puede insertar
            return ResponseEntity.status(HttpStatus.CONFLICT).body(2);
        }

        // Si no existe, proceder con la inserción
        boolean registroExitoso = this.iClienteService.registroComoEmpleado(cliente);
        System.out.println("ClienteTO boolean: " + registroExitoso);
        if (registroExitoso) {
            return ResponseEntity.status(HttpStatus.CREATED).body(1);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(0);
        }
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(0);
    }
}

	// 2.b: BUSCAR CLIENTES POR APELLIDO
	// http://localhost:8082/API/v1.0/Renta/empleados/buscarCliente?apellido=
	@GetMapping(path = "/buscarCliente", produces = "application/json")
	public ResponseEntity<List<ClienteTO>> buscarClienteApellido(@RequestParam(required = false) String apellido) {
		List<ClienteTO> lista = this.iClienteService.buscarPorApellido(apellido);

		for (ClienteTO clie : lista) {
			Link link = linkTo(methodOn(ClienteController.class).consultarReservasPorNumero(clie.getId()))
					.withRel("reservas");
			clie.add(link);
		}
		return ResponseEntity.status(HttpStatus.OK).body(lista);
	}

	// 2.b.1: Visualizar CLIENTES POR cedula
	// http://localhost:8082/API/v1.0/Renta/empleados/id
	@GetMapping(path = "/{cedula}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ClienteTO> buscarClienteCedula(@PathVariable String cedula) {

		System.out.println(cedula);
		ClienteTO cliente = this.iClienteService.buscarPorCedula(cedula);
		System.out.println(cedula);
		System.out.println(cliente);
		return ResponseEntity.status(HttpStatus.OK).body(cliente);

	}

	// 2.b.2: ACTUALIZAR TODOS LOS DATOS DEL CLIENTE apartir del id
	// http://localhost:8082/API/v1.0/Renta/empleados/id
	@PatchMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void actualizarClienteE(@RequestBody ClienteTO cliente, @PathVariable Integer id) {
		cliente.setId(id);
		this.iClienteService.actualizar(cliente);
	}

	// 2.b.3: ELIMINAR CLIENTE
	// http://localhost:8082/API/v1.0/Renta/empleados/id
	@DeleteMapping(path = "/{id}")
	public void borrarCliente(@PathVariable Integer id) {
		this.iClienteService.eliminar(id);

	}

	// VEHICULOS
	// 2.c INGRESAR VEHICULO
	// http://localhost:8082/API/v1.0/Renta/empleados/insertarVehiculo
	@PostMapping(path = "/insertarVehiculo", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Integer> insertarVehiculo(@RequestBody VehiculoTO vehiculo) {// en la inserción no se coloca
																						// el id

		try {

			boolean validar = this.iVehiculoService.validarVehiculo(vehiculo);

			if (!validar) {
				// Si no están llenos, retornar un código de error
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(3);
			}

			// Verificar si ya existe un vehículo con la misma placa
			boolean vehiculoExistente = this.iVehiculoService.existeVehiculoConPlaca(vehiculo.getPlaca());

			if (vehiculoExistente) {
				// Ya existe un vehículo con la misma placa, no se puede insertar
				return ResponseEntity.status(HttpStatus.CONFLICT).body(2);
			}

			// Si no existe, proceder con la inserción
			boolean registroExitoso = this.iVehiculoService.insertar(vehiculo);
			if (registroExitoso) {
				return ResponseEntity.status(HttpStatus.CREATED).body(1);
			} else {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(0);
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(0);
		}
	}

	// 2.d BUSCAR VEHICULO POR MARCA
	// http://localhost:8082/API/v1.0/Renta/empleados/buscarVehiculo?marca=
	@GetMapping(path = "/buscarVehiculo", produces = "application/json")
	public ResponseEntity<List<VehiculoTO>> buscarPorMarca(@RequestParam(required = false) String marca) {

		try {
			List<VehiculoTO> lista = this.iVehiculoService.buscarPorMarca(marca);

			for (VehiculoTO vehi : lista) {
				Link link = linkTo(methodOn(EmpleadoController.class).buscarPorMarca(marca)).withSelfRel();
				vehi.add(link);
			}
			return ResponseEntity.status(HttpStatus.OK).body(lista);
		} catch (Exception e) {
			// Manejar otras excepciones de manera más general
			System.out.println("Error al buscar los vehículos por marca: " + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); // Puedes devolver null o algún
																						// valor predeterminado según
																						// tus necesidades
		}
	}

	// 2.d.1: visualizar por Placa
	// http://localhost:8082/API/v1.0/Renta/empleados/buscarVehiculo/{placa}
	@GetMapping(path = "/buscarVehiculo/{placa}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<VehiculoTO> buscarVehiculoPlaca(@PathVariable String placa) {

		try {
			System.out.println(placa);
			VehiculoTO vehiculoTO = this.iVehiculoService.buscarPorPlaca(placa);
			System.out.println(placa);
			System.out.println(placa);
			return ResponseEntity.status(HttpStatus.OK).body(vehiculoTO);
		} catch (NoResultException e) {
			// Manejar la excepción cuando no se encuentra ningún resultado
			System.out.println("Vehículo no encontrado para la placa: " + placa);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // Puedes devolver null o algún valor
																			// predeterminado según tus necesidades
		} catch (Exception e) {
			// Manejar otras excepciones de manera más general
			System.out.println("Error al buscar el vehículo por placa: " + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); // Puedes devolver null o algún
																						// valor predeterminado según
																						// tus necesidades
		}
	}

	// 2.d.2: ACTUALIZAR TODOS LOS DATOS DEL VEHICULO (menos el estado)
	// http://localhost:8082/API/v1.0/Renta/empleados/actualizarVehiculo/{id}
	@PatchMapping(path = "/actualizarVehiculo/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void actualizarVehiculo(@RequestBody VehiculoTO vehiculo, @PathVariable Integer id) {// no se inserta el id
		vehiculo.setId(id);
		this.iVehiculoService.actualizar(vehiculo);

	}

	// 2.d: ELIMINAR VEHICULO (por id)
	// http://localhost:8082/API/v1.0/Renta/empleados/borrar/{id}
	@DeleteMapping(path = "/borrarVehiculo/{id}")
	public void borrarVehiculo(@PathVariable Integer id) {
		Vehiculo v = this.iVehiculoService.buscarPorId(id);
		if (v.getEstado().equals("No Disponible")) {
			System.out.println("No es posible eliminar");
		}
		this.iVehiculoService.eliminar(id);
	}
}