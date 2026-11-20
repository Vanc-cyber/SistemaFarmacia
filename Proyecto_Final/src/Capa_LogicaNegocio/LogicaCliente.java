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
            throw new IllegalArgumentException(
                    "DatosCliente es obligatorio."
            );
        }

        this.datosCliente = datosCliente;
    }

    // Registra un cliente después de validarlo
    public void registrarCliente(Cliente cliente) {

        validarCliente(cliente);

        // Limpia espacios antes de guardar
        limpiarDatos(cliente);

        if (datosCliente.buscarCliente(
                cliente.getIdentificacion()) != null) {

            throw new IllegalArgumentException(
                    "Ya existe un cliente con esa identificación."
            );
        }

        datosCliente.agregarCliente(cliente);
    }

    // Busca un cliente por su identificación
    public Cliente buscarCliente(String identificacion) {

        requerido(
                identificacion,
                "Debe indicar la identificación que desea buscar."
        );

        validarIdentificacion(identificacion.trim());

        return datosCliente.buscarCliente(
                identificacion.trim()
        );
    }

    // Devuelve los clientes registrados
    public ArrayList<Cliente> listarClientes() {

        return datosCliente.listarClientes();
    }

    // Modifica un cliente existente
    public boolean modificarCliente(Cliente cliente) {

        validarCliente(cliente);

        limpiarDatos(cliente);

        Cliente clienteExistente
                = datosCliente.buscarCliente(
                        cliente.getIdentificacion()
                );

        if (clienteExistente == null) {

            throw new IllegalArgumentException(
                    "El cliente que desea modificar no existe."
            );
        }

        return datosCliente.modificarCliente(cliente);
    }

    // Elimina un cliente por su identificación
    public boolean eliminarCliente(String identificacion) {

        requerido(
                identificacion,
                "Debe indicar la identificación del cliente."
        );

        identificacion = identificacion.trim();

        validarIdentificacion(identificacion);

        Cliente cliente
                = datosCliente.buscarCliente(
                        identificacion
                );

        if (cliente == null) {

            throw new IllegalArgumentException(
                    "El cliente que desea eliminar no existe."
            );
        }

        return datosCliente.eliminarCliente(
                identificacion
        );
    }

    // Valida todos los datos obligatorios del cliente
    private void validarCliente(Cliente cliente) {

        if (cliente == null) {

            throw new IllegalArgumentException(
                    "Debe indicar un cliente."
            );
        }

        requerido(
                cliente.getIdentificacion(),
                "La identificación es obligatoria."
        );

        validarIdentificacion(
                cliente.getIdentificacion().trim()
        );

        requerido(
                cliente.getNombre(),
                "El nombre es obligatorio."
        );

        requerido(
                cliente.getApellido1(),
                "El primer apellido es obligatorio."
        );

        requerido(
                cliente.getApellido2(),
                "El segundo apellido es obligatorio."
        );

        if (cliente.getFechaNacimiento() == null) {

            throw new IllegalArgumentException(
                    "La fecha de nacimiento es obligatoria."
            );
        }

        requerido(
                cliente.getDireccion(),
                "La dirección es obligatoria."
        );

        requerido(
                cliente.getCorreoElectronico(),
                "El correo electrónico es obligatorio."
        );

        requerido(
                cliente.getTelefono(),
                "El teléfono es obligatorio."
        );

        if (cliente.getPuntos() < 0) {

            throw new IllegalArgumentException(
                    "Los puntos no pueden ser negativos."
            );
        }
    }

    // Valida que la identificación tenga exactamente 9 dígitos
    private void validarIdentificacion(String identificacion) {

        if (!identificacion.matches("\\d{9}")) {

            throw new IllegalArgumentException(
                    "La identificación debe contener exactamente 9 dígitos."
            );
        }
    }

    // Elimina espacios innecesarios
    private void limpiarDatos(Cliente cliente) {

        cliente.setIdentificacion(
                cliente.getIdentificacion().trim()
        );

        cliente.setNombre(
                cliente.getNombre().trim()
        );

        cliente.setApellido1(
                cliente.getApellido1().trim()
        );

        cliente.setApellido2(
                cliente.getApellido2().trim()
        );

        cliente.setDireccion(
                cliente.getDireccion().trim()
        );

        cliente.setCorreoElectronico(
                cliente.getCorreoElectronico().trim()
        );

        cliente.setTelefono(
                cliente.getTelefono().trim()
        );
    }

    // Valida que un texto no sea nulo ni vacío
    private void requerido(
            String valor,
            String mensaje) {

        if (valor == null
                || valor.trim().isEmpty()) {

            throw new IllegalArgumentException(
                    mensaje
            );
        }
    }
}