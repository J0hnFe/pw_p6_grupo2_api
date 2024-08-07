package com.example.demo.repo;

import java.time.LocalDate;
import java.util.List;

import com.example.demo.repo.modelo.Cliente;

public interface IClienteRepo {
    public List<Cliente> buscarPorApellido(String apellido);

    public List<Cliente> buscarTodos();

    public void eliminar(Integer id);

    public boolean insertar(Cliente cliente);

    public Cliente buscarCedula(String cedula);

    public Cliente buscarPorId(Integer id);

    public void actualizar(Cliente cliente);

    public void actualizarParcial(String nombre, String apellido, LocalDate fechaNacimiento, String genero,
            String registro, Integer id);
}
