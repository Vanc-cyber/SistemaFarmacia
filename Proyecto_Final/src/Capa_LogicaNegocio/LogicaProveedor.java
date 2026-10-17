package Capa_LogicaNegocio;

import Capa_Datos.DatosProveedor;
import Capa_Entidades.Proveedor;
import java.util.ArrayList;

/** Valida los datos y coordina las operaciones de proveedores. */
public class LogicaProveedor {

    private final DatosProveedor datosProveedor;

    public LogicaProveedor() {
        this(new DatosProveedor());
    }

    public LogicaProveedor(DatosProveedor datosProveedor) {
        if (datosProveedor == null) {
            throw new IllegalArgumentException("DatosProveedor es obligatorio.");
        }
        this.datosProveedor = datosProveedor;
    }

    public void registrarProveedor(Proveedor proveedor) {
        if (proveedor == null) {
            throw new IllegalArgumentException("Debe indicar un proveedor.");
        }

        requerido(proveedor.getIdentificacion(), "La identificación es obligatoria.");
        requerido(proveedor.getNombre(), "El nombre es obligatorio.");
        requerido(proveedor.getEmpresa(), "La empresa es obligatoria.");

        proveedor.setIdentificacion(proveedor.getIdentificacion().trim());
        proveedor.setNombre(proveedor.getNombre().trim());
        proveedor.setEmpresa(proveedor.getEmpresa().trim());

        if (datosProveedor.buscarProveedor(proveedor.getIdentificacion()) != null) {
            throw new IllegalArgumentException(
                    "Ya existe un proveedor con esa identificación.");
        }

        datosProveedor.agregarProveedor(proveedor);
    }

    public Proveedor buscarProveedor(String identificacion) {
        requerido(identificacion, "Debe indicar la identificación que desea buscar.");
        return datosProveedor.buscarProveedor(identificacion.trim());
    }

    public ArrayList<Proveedor> listarProveedores() {
        return datosProveedor.listarProveedores();
    }

    private void requerido(String valor, String mensaje) {
        if (valor == null || valor.trim().isEmpty()) {
            throw new IllegalArgumentException(mensaje);
        }
    }
}
