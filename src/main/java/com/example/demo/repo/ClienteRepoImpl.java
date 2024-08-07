package com.example.demo.repo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.repo.modelo.Cliente;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class ClienteRepoImpl implements IClienteRepo {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    // @Transactional(value = TxType.NOT_SUPPORTED)
    public Cliente buscarCedula(String cedula) {
        String jpql = "SELECT c FROM Cliente c WHERE c.numeroCedula= :datoCedula";
        TypedQuery<Cliente> myQuery = this.entityManager.createQuery(jpql, Cliente.class);
        myQuery.setParameter("datoCedula", cedula);
        return myQuery.getSingleResult();
    }

    @Override
    // @Transactional(value = TxType.MANDATORY)
    public boolean insertar(Cliente cliente) {
        try {
            this.entityManager.persist(cliente);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    // @Transactional(value = TxType.NOT_SUPPORTED)
    public List<Cliente> buscarPorApellido(String apellido) {
        String jpql = "SELECT c FROM Cliente c WHERE c.apellido = :datoApellido";
        TypedQuery<Cliente> myQuery = this.entityManager.createQuery(jpql, Cliente.class);
        myQuery.setParameter("datoApellido", apellido);
        return myQuery.getResultList();
    }

    @Override
    // @Transactional(value = TxType.NOT_SUPPORTED)
    public Cliente buscarPorId(Integer id) {
        return this.entityManager.find(Cliente.class, id);
    }

    @Override
    // @Transactional(value = TxType.MANDATORY)
    public void eliminar(Integer id) {
        this.entityManager.remove(this.buscarPorId(id));
    }

    @Override
    // @Transactional(value = TxType.MANDATORY)
    public void actualizar(Cliente cliente) {
        String jpql = "UPDATE Cliente c SET c.nombre= :valor1, c.apellido= : valor2, c.numeroCedula= :valor3, c.fechaNacimiento= : valor4, c.genero= :valor5 WHERE c.id= :valor6";
        Query query = this.entityManager.createQuery(jpql);

        query.setParameter("valor1", cliente.getNombre());
        query.setParameter("valor2", cliente.getApellido());
        query.setParameter("valor3", cliente.getNumeroCedula());
        query.setParameter("valor4", cliente.getFechaNacimiento());
        query.setParameter("valor5", cliente.getGenero());
        query.setParameter("valor6", cliente.getId());
        query.executeUpdate();
    }

    @Override
    // @Transactional(value = TxType.NOT_SUPPORTED)
    public List<Cliente> buscarTodos() {
        String jpql = "SELECT c FROM Cliente c";
        TypedQuery<Cliente> myQuery = this.entityManager.createQuery(jpql, Cliente.class);
        return myQuery.getResultList();
    }

    @Override
    public void actualizarParcial(String nombre, String apellido, LocalDate fechaNacimiento, String genero,
            String registro, Integer id) {

        String jpql = "UPDATE Cliente c SET c.nombre =:valor1, c.apellido =:valor2, c.fechaNacimiento =: valor3, c.genero =: valor4, c.registro=:valor5 WHERE c.id =: valor6";
        Query query = this.entityManager.createQuery(jpql);

        query.setParameter("valor1", nombre);
        query.setParameter("valor2", apellido);
        query.setParameter("valor3", fechaNacimiento);
        query.setParameter("valor4", genero);
        query.setParameter("valor5", "C");
        query.setParameter("valor6", id);

        query.executeUpdate();

    }
}
