package com.example.demo.service;

import java.util.List;

import com.example.demo.repo.modelo.Vehiculo;
import com.example.demo.repo.modelo.DTO.VehiculoDTO;
import com.example.demo.service.TO.VehiculoTO;

public interface IVehiculoService {
    public List<Vehiculo> vehiculosDisponibles(String marca, String modelo);

    public Vehiculo buscarPorId(Integer id);

    public boolean insertar(VehiculoTO vehiculo);

    public void actualizar(VehiculoTO vehiculo);

    public void eliminar(Integer id);

    public List<Vehiculo> buscarTodos();

    public List<VehiculoTO> buscarPorMarca(String marca);

    public VehiculoTO buscarPorPlaca(String placa);

    public List<Vehiculo> buscarFechas(String fechaInicio, String fechaFin);

    public List<VehiculoTO> buscarPorMarcayModelo(String marca, String Modelo);

    public List<VehiculoDTO> buscarVehiculosPorMarcayModelo(String marca, String modelo);

    public boolean existeVehiculoConPlaca(String placa);

    public boolean validarVehiculo(VehiculoTO vehiculo);
}
