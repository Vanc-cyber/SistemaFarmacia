package Capa_Datos;

import Capa_Entidades.Cliente;
import java.util.ArrayList;

/**
 * Administra la colección de clientes en memoria.
 * Las reglas de negocio, como impedir identificaciones duplicadas,
 * corresponden a LogicaCliente.
 */
public class DatosCliente {

    private final ArrayList<Cliente> listaClientes = new ArrayList<>();

    /** Agrega un cliente a la colección. */
    public void agregarCliente(Cliente cliente) {
        if (cliente == null) {
            throw new IllegalArgumentException("El cliente no puede ser null.");
        }
        listaClientes.add(cliente);
    }

    /** Busca un cliente por identificación, ignorando mayúsculas y espacios exteriores. */
    public Cliente buscarCliente(String identificacion) {
        String identificacionBuscada = normalizarIdentificacion(identificacion);
        if (identificacionBuscada == null) {
            return null;
        }

        for (Cliente cliente : listaClientes) {
            if (cliente == null) {
                continue;
            }

            String identificacionGuardada = normalizarIdentificacion(cliente.getIdentificacion());
            if (identificacionBuscada.equalsIgnoreCase(identificacionGuardada)) {
                return cliente;
            }
        }
        return null;
    }

    /** Devuelve una copia de la lista para proteger la estructura interna. */
    public ArrayList<Cliente> listarClientes() {
        return new ArrayList<>(listaClientes);
    }

    /** Reemplaza el cliente que tenga la misma identificación. */
    public boolean modificarCliente(Cliente cliente) {
        if (cliente == null) {
            return false;
        }

        String identificacionBuscada = normalizarIdentificacion(cliente.getIdentificacion());
        if (identificacionBuscada == null) {
            return false;
        }

        for (int i = 0; i < listaClientes.size(); i++) {
            Cliente clienteGuardado = listaClientes.get(i);
            if (clienteGuardado == null) {
                continue;
            }

            String identificacionGuardada = normalizarIdentificacion(clienteGuardado.getIdentificacion());
            if (identificacionBuscada.equalsIgnoreCase(identificacionGuardada)) {
                listaClientes.set(i, cliente);
                return true;
            }
        }
        return false;
    }

    /** Elimina el cliente que coincida con la identificación indicada. */
    public boolean eliminarCliente(String identificacion) {
        String identificacionBuscada = normalizarIdentificacion(identificacion);
        if (identificacionBuscada == null) {
            return false;
        }

        for (int i = 0; i < listaClientes.size(); i++) {
            Cliente cliente = listaClientes.get(i);
            if (cliente == null) {
                continue;
            }

            String identificacionGuardada = normalizarIdentificacion(cliente.getIdentificacion());
            if (identificacionBuscada.equalsIgnoreCase(identificacionGuardada)) {
                listaClientes.remove(i);
                return true;
            }
        }
        return false;
    }

    private String normalizarIdentificacion(String identificacion) {
        if (identificacion == null) {
            return null;
        }
        String normalizada = identificacion.trim();
        return normalizada.isEmpty() ? null : normalizada;
    }
}
