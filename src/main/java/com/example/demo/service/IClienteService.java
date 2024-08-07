package com.example.demo.service;

import java.time.LocalDate;
import java.util.List;

import com.example.demo.repo.modelo.Cliente;
import com.example.demo.service.TO.ClienteTO;

public interface IClienteService {
    public boolean registro(ClienteTO cliente);

    public boolean registroComoEmpleado(ClienteTO cliente);

    public List<ClienteTO> buscarPorApellido(String apellido);

    public void eliminar(Integer id);

    public Cliente buscarPorId(Integer id);

    public void actualizar(ClienteTO cliente);

    public ClienteTO buscarPorCedula(String cedula);

    public List<ClienteTO> buscarTodos();

    public void actualizarParcial(String nombre, String apellido, LocalDate fechaNacimiento, String genero,
            String registro, Integer id);

    public boolean existeClienteConCedula(String placa);

    public boolean validarCliente(ClienteTO cliente);
}
