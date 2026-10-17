package Capa_LogicaNegocio;

import Capa_Datos.DatosCliente;
import Capa_Entidades.Cliente;
import java.util.ArrayList;

/** Aplica las validaciones y coordina las operaciones de clientes. */
public class LogicaCliente {

    private final DatosCliente datosCliente;

    public LogicaCliente() {
        this(new DatosCliente());
    }

    /** Permite compartir DatosCliente con otras clases o pruebas. */
    public LogicaCliente(DatosCliente datosCliente) {
        if (datosCliente == null) {
            throw new IllegalArgumentException("DatosCliente es obligatorio.");
        }
        this.datosCliente = datosCliente;
    }

    /** Valida y registra un cliente. */
    public void registrarCliente(Cliente cliente) {
        if (cliente == null) {
            throw new IllegalArgumentException("Debe indicar un cliente.");
        }

        requerido(cliente.getIdentificacion(), "La identificación es obligatoria.");
        requerido(cliente.getNombre(), "El nombre es obligatorio.");

        // Normaliza los campos de texto usados como identificador y nombre.
        cliente.setIdentificacion(cliente.getIdentificacion().trim());
        cliente.setNombre(cliente.getNombre().trim());

        if (cliente.getPuntos() < 0) {
            throw new IllegalArgumentException("Los puntos no pueden ser negativos.");
        }

        if (datosCliente.buscarCliente(cliente.getIdentificacion()) != null) {
            throw new IllegalArgumentException("Ya existe un cliente con esa identificación.");
        }

        datosCliente.agregarCliente(cliente);
    }

    /** Busca un cliente después de validar la identificación recibida. */
    public Cliente buscarCliente(String identificacion) {
        requerido(identificacion, "Debe indicar la identificación que desea buscar.");
        return datosCliente.buscarCliente(identificacion.trim());
    }

    /** Devuelve los clientes registrados. */
    public ArrayList<Cliente> listarClientes() {
        return datosCliente.listarClientes();
    }

    private void requerido(String valor, String mensaje) {
        if (valor == null || valor.trim().isEmpty()) {
            throw new IllegalArgumentException(mensaje);
        }
    }
}
