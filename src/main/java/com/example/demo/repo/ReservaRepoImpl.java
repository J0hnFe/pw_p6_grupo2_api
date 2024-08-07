package com.example.demo.repo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.repo.modelo.Reserva;
import com.example.demo.service.TO.ReporteTO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class ReservaRepoImpl implements IReservaRepo {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	// @Transactional(value = TxType.MANDATORY)
	public void guardar(Reserva r) {
		this.entityManager.persist(r);
	}

	@Override
	// @Transactional(value = TxType.MANDATORY)
	public void actualizarReserva(Reserva r) {
		this.entityManager.merge(r);
	}

	@Override
	// @Transactional(value = TxType.NOT_SUPPORTED)
	public List<Reserva> buscarReserva(String placa) {
		TypedQuery<Reserva> myQuery = this.entityManager.createQuery(
				"SELECT r FROM Reserva r INNER JOIN r.vehiculo v WHERE v.placa=:datoVehiculo", Reserva.class);
		myQuery.setParameter("datoVehiculo", placa);
		return myQuery.getResultList();
	}

	@Override
	// @Transactional(value = TxType.NOT_SUPPORTED)
	public Reserva buscarAutoReserva(String numeroReserva) {
		TypedQuery<Reserva> myQuery = this.entityManager
				.createQuery("SELECT r FROM Reserva r WHERE r.numeroReserva=:datoNumero", Reserva.class);
		myQuery.setParameter("datoNumero", numeroReserva);
		return myQuery.getSingleResult();
	}

	@Override
	// @Transactional(value = TxType.NOT_SUPPORTED)
	public Reserva buscarPlaca(Integer id, String placa) {
		TypedQuery<Reserva> myQuery = this.entityManager.createQuery(
				"SELECT r FROM Reserva r INNER JOIN r.vehiculo v WHERE r.id=:datoId AND v.placa=:datoVehiculo",
				Reserva.class);
		myQuery.setParameter("datoId", id);
		myQuery.setParameter("datoVehiculo", placa);
		return myQuery.getSingleResult();
	}

	@Override
	// @Transactional(value = TxType.NOT_SUPPORTED)
	public Reserva buscarReservaPlaca(String placa) {
		TypedQuery<Reserva> myQuery = this.entityManager.createQuery(
				"SELECT r FROM Reserva r INNER JOIN r.vehiculo v WHERE v.placa=:datoVehiculo", Reserva.class);
		myQuery.setParameter("datoVehiculo", placa);
		return myQuery.getSingleResult();
	}

	@Override
	// @Transactional(value = TxType.NOT_SUPPORTED)
	public List<Reserva> buscarPorFechas(LocalDate fechaInicio, LocalDate fechaFin) {
		TypedQuery<Reserva> myQuery = this.entityManager.createQuery(
				"SELECT r FROM Reserva r WHERE r.fechaInicio >= :datoFechaInicio AND r.fechaFin <= :datoFechaFin",
				Reserva.class);
		myQuery.setParameter("datoFechaInicio", fechaInicio);
		myQuery.setParameter("datoFechaFin", fechaFin);

		return myQuery.getResultList();
	}

	@Override
	public List<Reserva> seleccionarPorIdCiente(Integer id) {
		// TODO Auto-generated method stub
		TypedQuery<Reserva> myQuery = this.entityManager.createQuery("SELECT r FROM Reserva r WHERE r.cliente.id =: id",
				Reserva.class);
		myQuery.setParameter("id", id);
		return myQuery.getResultList();
	}

	@Override
	public List<ReporteTO> seleccionarListaPorFechas(LocalDate fechaInicio, LocalDate fechaFin) {

		String jpql = "SELECT NEW com.example.demo.service.TO.ReporteTO" +
				"(r.numeroReserva, r.diasReserva, r.fechaInicio, r.fechaFin, r.estado," +
				" c.apellido, c.numeroCedula," +
				" v.placa, v.modelo, v.marca)" +
				" FROM Reserva r" +
				" INNER JOIN r.cliente c " +
				"INNER JOIN r.vehiculo v " +
				"WHERE r.fechaInicio >= :datoFechaInicio AND r.fechaFin <= :datoFechaFin";

		TypedQuery<ReporteTO> myQuery = this.entityManager.createQuery(jpql, ReporteTO.class);
		myQuery.setParameter("datoFechaInicio", fechaInicio);
		myQuery.setParameter("datoFechaFin", fechaFin);
		return myQuery.getResultList();
	}

	@Override
	public List<Reserva> seleccionarReservasPorVehiculo(String placaVehiculo) {
		TypedQuery<Reserva> myQuery = this.entityManager.createQuery(
				"SELECT r FROM Reserva r WHERE r.vehiculo.placa = :placaVehiculo",
				Reserva.class);
		myQuery.setParameter("placaVehiculo", placaVehiculo);
		return myQuery.getResultList();
	}
}
