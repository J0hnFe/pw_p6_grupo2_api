package com.example.demo.repo;

import java.util.List;

import com.example.demo.repo.modelo.Vehiculo;
import com.example.demo.repo.modelo.DTO.VehiculoDTO;

public interface IVehiculoRepo {
    public boolean ingresarVehiculo(Vehiculo v);

    public void actualizarEstado(Vehiculo v);

    public List<Vehiculo> vehiculosDisponibles(String marca, String modelo);

    public Vehiculo buscarPorPlaca(String placa);

    public Vehiculo buscarPorId(Integer id);

    public void eliminar(Integer id);

    public List<Vehiculo> buscarTodos();

    public List<Vehiculo> buscarPorMarca(String marca);

    public List<Vehiculo> buscarPorFechas(String fechaInicio, String fechaFin);

    public List<Vehiculo> buscarPorMarcayModelo(String marca, String Modelo);

    // 1.a: Buscar vehículos disponibles
    public List<VehiculoDTO> buscarVehiculosPorMarcayModelo(String marca, String modelo);
}
