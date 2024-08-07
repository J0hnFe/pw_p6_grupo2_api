package com.example.demo.repo;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Repository;

import com.example.demo.repo.modelo.Vehiculo;
import com.example.demo.repo.modelo.DTO.VehiculoDTO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class VehiculoRepoImpl implements IVehiculoRepo {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    // @Transactional(value = TxType.MANDATORY)
    public boolean ingresarVehiculo(Vehiculo vehiculo) {

        try {
            this.entityManager.persist(vehiculo);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    // @Transactional(value = TxType.MANDATORY)
    public void actualizarEstado(Vehiculo vh) {
        String jpql = "UPDATE Vehiculo v SET v.placa= :valor1, v.marca= : valor2, v.modelo= :valor3, v.anioFabricacion= : valor4, v.paisFabricacion= :valor5, v.cilindraje= : valor6, v.avaluo= :valor7, v.renta= : valor8 WHERE v.id= :valor9";
        Query query = this.entityManager.createQuery(jpql);

        query.setParameter("valor1", vh.getPlaca());
        query.setParameter("valor2", vh.getMarca());
        query.setParameter("valor3", vh.getModelo());
        query.setParameter("valor4", vh.getAnioFabricacion());
        query.setParameter("valor5", vh.getPaisFabricacion());
        query.setParameter("valor6", vh.getCilindraje());
        query.setParameter("valor7", vh.getAvaluo());
        query.setParameter("valor8", vh.getRenta());
        query.setParameter("valor9", vh.getId());

        query.executeUpdate();
    }

    @Override
    // @Transactional(value = TxType.NOT_SUPPORTED)
    public List<Vehiculo> vehiculosDisponibles(String marca, String modelo) {
        String jpql = "SELECT v FROM Vehiculo v WHERE v.marca=:datoMarca AND v.modelo=:datoModelo";
        TypedQuery<Vehiculo> myQuery = this.entityManager.createQuery(jpql, Vehiculo.class);
        myQuery.setParameter("datoMarca", marca);
        myQuery.setParameter("datoModelo", modelo);
        return myQuery.getResultList();
    }

    @Override
    // @Transactional(value = TxType.NOT_SUPPORTED)
    public Vehiculo buscarPorPlaca(String placa) {

        try {
            String jpql = "SELECT v FROM Vehiculo v WHERE v.placa=:datoPlaca";
            TypedQuery<Vehiculo> myQuery = this.entityManager.createQuery(jpql, Vehiculo.class);
            myQuery.setParameter("datoPlaca", placa);
            return myQuery.getSingleResult();

        } catch (Exception e) {
            // Manejar otras excepciones de manera más general
            System.out.println("Error al buscar el vehículo por placa: " + e.getMessage());
            return null;
        }

    }

    @Override
    // @Transactional(value = TxType.NOT_SUPPORTED)
    public Vehiculo buscarPorId(Integer id) {
        return this.entityManager.find(Vehiculo.class, id);
    }

    @Override
    // @Transactional(value = TxType.MANDATORY)
    public void eliminar(Integer id) {
        Vehiculo v = this.buscarPorId(id);
        this.entityManager.remove(v);
    }

    @Override
    // @Transactional(value = TxType.NOT_SUPPORTED)
    public List<Vehiculo> buscarTodos() {
        String jpql = "SELECT v FROM Vehiculo v";
        TypedQuery<Vehiculo> myQuery = this.entityManager.createQuery(jpql, Vehiculo.class);
        return myQuery.getResultList();
    }

    @Override
    // @Transactional(value = TxType.NOT_SUPPORTED)
    public List<Vehiculo> buscarPorMarca(String marca) {

        try {
            String jpql = "SELECT v FROM Vehiculo v WHERE v.marca =:datoMarca";

            TypedQuery<Vehiculo> myQuery = this.entityManager.createQuery(jpql, Vehiculo.class);
            myQuery.setParameter("datoMarca", marca);
            return myQuery.getResultList();
        } catch (Exception e) {
            // Manejar otras excepciones de manera más general
            System.out.println("Error al buscar el vehículo por placa: " + e.getMessage());
        }
        return null;

    }

    @Override
    // @Transactional(value = TxType.NOT_SUPPORTED)
    public List<Vehiculo> buscarPorFechas(String fechaInicio, String fechaFin) {

        String jpql = "Select v from Vehiculo v JOIN v.reservas r WHERE r.fechaInicio >= :datoFechaInicio AND r.fechaFin <= :datoFechaFin ";

        TypedQuery<Vehiculo> myQuery = this.entityManager.createQuery(jpql, Vehiculo.class);
        myQuery.setParameter("datoFechaInicio", fechaInicio);
        myQuery.setParameter("datoFechaFin", fechaFin);

        List<Vehiculo> listaVehiculos = myQuery.getResultList();

        Set<Vehiculo> vehiculosUnicos = new HashSet<Vehiculo>(listaVehiculos);
        listaVehiculos.clear();
        listaVehiculos.addAll(vehiculosUnicos);

        return listaVehiculos;

    }

    @Override
    public List<Vehiculo> buscarPorMarcayModelo(String marca, String modelo) {

        String jpql = "SELECT v FROM Vehiculo v WHERE v.marca =:datoMarca OR v.modelo =: datoModelo";

        TypedQuery<Vehiculo> myQuery = this.entityManager.createQuery(jpql, Vehiculo.class);
        myQuery.setParameter("datoMarca", marca);
        myQuery.setParameter("datoModelo", modelo);
        return myQuery.getResultList();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<VehiculoDTO> buscarVehiculosPorMarcayModelo(String marca, String modelo) {
        String jpql = "SELECT NEW com.example.demo.repo.modelo.DTO.VehiculoDTO(v.placa, v.modelo, v.marca, v.anioFabricacion, v.estado, v.renta) "
                + "FROM Vehiculo v WHERE v.marca = :datoMarca AND v.modelo = :datoModelo";
    
        Query myQuery = this.entityManager.createQuery(jpql);
        myQuery.setParameter("datoMarca", marca);
        myQuery.setParameter("datoModelo", modelo);
        return myQuery.getResultList();
    }

}
