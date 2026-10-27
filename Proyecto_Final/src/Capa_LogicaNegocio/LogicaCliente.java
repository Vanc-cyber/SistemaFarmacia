
package Capa_LogicaNegocio;

import Capa_Datos.DatosCliente;
import Capa_Entidades.Cliente;
import java.util.ArrayList;

public class LogicaCliente {

    // Acceso a la capa de datos de clientes
    private final DatosCliente datosCliente;

    // Constructor por defecto
    public LogicaCliente() {
        this(new DatosCliente());
    }

    // Constructor que recibe la capa de datos
    public LogicaCliente(DatosCliente datosCliente) {
        if (datosCliente == null) {
            throw new IllegalArgumentException("DatosCliente es obligatorio.");
        }
        this.datosCliente = datosCliente;
    }

    // Registra un cliente después de validarlo
    public void registrarCliente(Cliente cliente) {
        if (cliente == null) {
            throw new IllegalArgumentException("Debe indicar un cliente.");
        }

        requerido(cliente.getIdentificacion(), "La identificación es obligatoria.");
        requerido(cliente.getNombre(), "El nombre es obligatorio.");

        // Limpia espacios de identificación y nombre
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

    // Busca un cliente por su identificación
    public Cliente buscarCliente(String identificacion) {
        requerido(identificacion, "Debe indicar la identificación que desea buscar.");
        return datosCliente.buscarCliente(identificacion.trim());
    }

    // Devuelve los clientes registrados
    public ArrayList<Cliente> listarClientes() {
        return datosCliente.listarClientes();
    }

    // Modifica un cliente existente
    public boolean modificarCliente(Cliente cliente) {

        if (cliente == null) {
            throw new IllegalArgumentException("Debe indicar un cliente.");
        }

        requerido(
                cliente.getIdentificacion(),
                "La identificación es obligatoria."
        );

        requerido(
                cliente.getNombre(),
                "El nombre es obligatorio."
        );

        if (cliente.getPuntos() < 0) {
            throw new IllegalArgumentException(
                    "Los puntos no pueden ser negativos."
            );
        }

        Cliente clienteExistente
                = datosCliente.buscarCliente(
                        cliente.getIdentificacion()
                );

        if (clienteExistente == null) {
            throw new IllegalArgumentException(
                    "El cliente que desea modificar no existe."
            );
        }

        cliente.setIdentificacion(
                cliente.getIdentificacion().trim()
        );

        cliente.setNombre(
                cliente.getNombre().trim()
        );

        return datosCliente.modificarCliente(cliente);
    }

    // Elimina un cliente por su identificación
    public boolean eliminarCliente(String identificacion) {

        requerido(
                identificacion,
                "Debe indicar la identificación del cliente."
        );

        Cliente cliente
                = datosCliente.buscarCliente(
                        identificacion.trim()
                );

        if (cliente == null) {
            throw new IllegalArgumentException(
                    "El cliente que desea eliminar no existe."
            );
        }

        return datosCliente.eliminarCliente(
                identificacion.trim()
        );
    }

    // Valida que un texto no sea nulo ni vacío
    private void requerido(String valor, String mensaje) {
        if (valor == null || valor.trim().isEmpty()) {
            throw new IllegalArgumentException(mensaje);
        }
    }
}