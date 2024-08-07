package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repo.IVehiculoRepo;
import com.example.demo.repo.modelo.Vehiculo;
import com.example.demo.repo.modelo.DTO.VehiculoDTO;
import com.example.demo.service.TO.VehiculoTO;

@Service
public class VehiculoServiceImpl implements IVehiculoService {

    @Autowired
    private IVehiculoRepo iVehiculoRepository;

    @Override
    // @Transactional(value = TxType.REQUIRES_NEW)
    public List<Vehiculo> vehiculosDisponibles(String marca, String modelo) {
        return this.iVehiculoRepository.vehiculosDisponibles(marca, modelo);
    }

    @Override
    // @Transactional(value = TxType.REQUIRES_NEW)
    public Vehiculo buscarPorId(Integer id) {
        return this.iVehiculoRepository.buscarPorId(id);
    }

    @Override
    // @Transactional(value = TxType.REQUIRES_NEW)
    public boolean insertar(VehiculoTO vehiculo) {
        vehiculo.setEstado("Disponible");
        Vehiculo vehi = this.convertirTOaVehiculo(vehiculo);
        return this.iVehiculoRepository.ingresarVehiculo(vehi);
    }

    private Vehiculo convertirTOaVehiculo(VehiculoTO vehiculo) {

        Vehiculo vehi = new Vehiculo();

        vehi.setId(vehiculo.getId());
        vehi.setPlaca(vehiculo.getPlaca());
        vehi.setModelo(vehiculo.getModelo());
        vehi.setMarca(vehiculo.getMarca());
        vehi.setAnioFabricacion(vehiculo.getAnioFabricacion());
        vehi.setEstado(vehiculo.getEstado());
        vehi.setPaisFabricacion(vehiculo.getPaisFabricacion());
        vehi.setCilindraje(vehiculo.getCilindraje());
        vehi.setAvaluo(vehiculo.getAvaluo());
        vehi.setRenta(vehiculo.getRenta());

        return vehi;
    }

    @Override
    // @Transactional(value = TxType.REQUIRES_NEW)
    public void actualizar(VehiculoTO vehiculo) {

        Vehiculo vehi = this.convertirToVehiculo(vehiculo);
        this.iVehiculoRepository.actualizarEstado(vehi);
        ;
    }

    private Vehiculo convertirToVehiculo(VehiculoTO v) {

        Vehiculo vehi = new Vehiculo();

        vehi.setId(v.getId());
        vehi.setPlaca(v.getPlaca());
        vehi.setModelo(v.getModelo());
        vehi.setMarca(v.getMarca());
        vehi.setAnioFabricacion(v.getAnioFabricacion());
        vehi.setEstado(v.getEstado());
        vehi.setPaisFabricacion(v.getPaisFabricacion());
        vehi.setCilindraje(v.getCilindraje());
        vehi.setAvaluo(v.getAvaluo());
        vehi.setRenta(v.getRenta());

        return vehi;
    }

    @Override
    // @Transactional(value = TxType.REQUIRES_NEW)
    public void eliminar(Integer id) {
        this.iVehiculoRepository.eliminar(id);
    }

    @Override
    // @Transactional(value = TxType.REQUIRES_NEW)
    public List<Vehiculo> buscarTodos() {
        return this.iVehiculoRepository.buscarTodos();
    }

    @Override
    // @Transactional(value = TxType.REQUIRES_NEW)
    public List<VehiculoTO> buscarPorMarca(String marca) {

        List<Vehiculo> lista = this.iVehiculoRepository.buscarPorMarca(marca);
        List<VehiculoTO> listaFinal = new ArrayList<>();

        for (Vehiculo vehi : lista) {
            listaFinal.add(this.convertir(vehi));
        }
        return listaFinal;

    }

    @Override
    // @Transactional(value = TxType.REQUIRES_NEW)
    public VehiculoTO buscarPorPlaca(String placa) {
        Vehiculo vehiculo = this.iVehiculoRepository.buscarPorPlaca(placa);
        return convertir(vehiculo);
    }

    @Override
    // @Transactional(value = TxType.REQUIRES_NEW)
    public List<Vehiculo> buscarFechas(String fechaInicio, String fechaFin) {
        return this.iVehiculoRepository.buscarPorFechas(fechaInicio, fechaFin);
    }

    @Override
    public List<VehiculoTO> buscarPorMarcayModelo(String marca, String Modelo) {
        List<Vehiculo> lista = this.iVehiculoRepository.buscarPorMarcayModelo(marca, Modelo);
        List<VehiculoTO> listaFinal = new ArrayList<>();

        for (Vehiculo vehi : lista) {
            listaFinal.add(this.convertir(vehi));
        }
        return listaFinal;
    }

    private VehiculoTO convertir(Vehiculo v) {
        VehiculoTO vehiTO = new VehiculoTO();

        vehiTO.setId(v.getId());
        vehiTO.setPlaca(v.getPlaca());
        vehiTO.setModelo(v.getModelo());
        vehiTO.setMarca(v.getMarca());
        vehiTO.setAnioFabricacion(v.getAnioFabricacion());
        vehiTO.setEstado(v.getEstado());
        vehiTO.setPaisFabricacion(v.getPaisFabricacion());
        vehiTO.setCilindraje(v.getCilindraje());
        vehiTO.setAvaluo(v.getAvaluo());
        vehiTO.setRenta(v.getRenta());

        return vehiTO;
    }

    @Override
    public List<VehiculoDTO> buscarVehiculosPorMarcayModelo(String marca, String modelo) {
        return this.iVehiculoRepository.buscarVehiculosPorMarcayModelo(marca, modelo);
    }

    @Override
    public boolean existeVehiculoConPlaca(String placa) {
        try {
            VehiculoTO vehiculo = this.buscarPorPlaca(placa);
            return vehiculo != null;
        } catch (Exception e) {
            // Manejar la excepción según tus necesidades
            return false;
        }
    }

    public boolean validarVehiculo(VehiculoTO vehiculo) {
        // Verificar que el objeto no sea nulo
        if ((vehiculo.getPlaca() == null || vehiculo.getMarca() == null)) {
            return false;
        }
        return true;
    }
}
