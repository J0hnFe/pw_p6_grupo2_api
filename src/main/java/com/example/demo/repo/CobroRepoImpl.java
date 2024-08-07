package com.example.demo.repo;

import org.springframework.stereotype.Repository;

import com.example.demo.repo.modelo.Cobro;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class CobroRepoImpl implements ICobroRepo {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    // @Transactional(value = TxType.MANDATORY)
    public void insertarPago(Cobro c) {
        this.entityManager.persist(c);
    }
}
