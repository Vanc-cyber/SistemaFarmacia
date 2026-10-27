
package Capa_LogicaNegocio;

import Capa_Datos.DatosProveedor;
import Capa_Entidades.Proveedor;
import java.util.ArrayList;

public class LogicaProveedor {

    // Acceso a la capa de datos de proveedores
    private final DatosProveedor datosProveedor;

    // Constructor por defecto
    public LogicaProveedor() {
        this(new DatosProveedor());
    }

    // Constructor que recibe la capa de datos
    public LogicaProveedor(DatosProveedor datosProveedor) {
        if (datosProveedor == null) {
            throw new IllegalArgumentException("DatosProveedor es obligatorio.");
        }
        this.datosProveedor = datosProveedor;
    }

    // Registra un proveedor después de validarlo
    public void registrarProveedor(Proveedor proveedor) {
        if (proveedor == null) {
            throw new IllegalArgumentException("Debe indicar un proveedor.");
        }

        requerido(proveedor.getIdentificacion(), "La identificación es obligatoria.");
        requerido(proveedor.getNombre(), "El nombre es obligatorio.");
        requerido(proveedor.getEmpresa(), "La empresa es obligatoria.");

        // Limpia espacios de los datos principales
        proveedor.setIdentificacion(proveedor.getIdentificacion().trim());
        proveedor.setNombre(proveedor.getNombre().trim());
        proveedor.setEmpresa(proveedor.getEmpresa().trim());

        if (datosProveedor.buscarProveedor(proveedor.getIdentificacion()) != null) {
            throw new IllegalArgumentException(
                    "Ya existe un proveedor con esa identificación.");
        }

        datosProveedor.agregarProveedor(proveedor);
    }

    // Busca un proveedor por su identificación
    public Proveedor buscarProveedor(String identificacion) {
        requerido(identificacion, "Debe indicar la identificación que desea buscar.");
        return datosProveedor.buscarProveedor(identificacion.trim());
    }

    // Devuelve los proveedores registrados
    public ArrayList<Proveedor> listarProveedores() {
        return datosProveedor.listarProveedores();
    }

    // Modifica un proveedor existente
    public boolean modificarProveedor(Proveedor proveedor) {

        if (proveedor == null) {
            throw new IllegalArgumentException(
                    "Debe indicar un proveedor."
            );
        }

        requerido(
                proveedor.getIdentificacion(),
                "La identificación es obligatoria."
        );

        requerido(
                proveedor.getNombre(),
                "El nombre es obligatorio."
        );

        requerido(
                proveedor.getEmpresa(),
                "La empresa es obligatoria."
        );

        Proveedor proveedorExistente
                = datosProveedor.buscarProveedor(
                        proveedor.getIdentificacion()
                );

        if (proveedorExistente == null) {
            throw new IllegalArgumentException(
                    "El proveedor que desea modificar no existe."
            );
        }

        proveedor.setIdentificacion(
                proveedor.getIdentificacion().trim()
        );

        proveedor.setNombre(
                proveedor.getNombre().trim()
        );

        proveedor.setEmpresa(
                proveedor.getEmpresa().trim()
        );

        return datosProveedor.modificarProveedor(proveedor);
    }

    // Elimina un proveedor por su identificación
    public boolean eliminarProveedor(String identificacion) {

        requerido(
                identificacion,
                "Debe indicar la identificación del proveedor."
        );

        Proveedor proveedor
                = datosProveedor.buscarProveedor(
                        identificacion.trim()
                );

        if (proveedor == null) {
            throw new IllegalArgumentException(
                    "El proveedor que desea eliminar no existe."
            );
        }

        return datosProveedor.eliminarProveedor(
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
