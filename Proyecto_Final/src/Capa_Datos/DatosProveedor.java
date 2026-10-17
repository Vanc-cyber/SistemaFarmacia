package Capa_Datos;

import Capa_Entidades.Proveedor;
import java.util.ArrayList;

/**
 * Almacena y consulta proveedores en memoria.
 * Las validaciones de negocio se realizan en LogicaProveedor.
 */
public class DatosProveedor {

    private final ArrayList<Proveedor> listaProveedores = new ArrayList<>();

    public void agregarProveedor(Proveedor proveedor) {
        if (proveedor == null) {
            throw new IllegalArgumentException("El proveedor no puede ser null.");
        }
        listaProveedores.add(proveedor);
    }

    public Proveedor buscarProveedor(String identificacion) {
        String buscada = normalizar(identificacion);
        if (buscada == null) {
            return null;
        }

        for (Proveedor proveedor : listaProveedores) {
            if (proveedor == null) {
                continue;
            }
            String guardada = normalizar(proveedor.getIdentificacion());
            if (guardada != null && buscada.equalsIgnoreCase(guardada)) {
                return proveedor;
            }
        }
        return null;
    }

    public ArrayList<Proveedor> listarProveedores() {
        return new ArrayList<>(listaProveedores);
    }

    public boolean modificarProveedor(Proveedor proveedor) {
        if (proveedor == null) {
            return false;
        }
        String buscada = normalizar(proveedor.getIdentificacion());
        if (buscada == null) {
            return false;
        }

        for (int i = 0; i < listaProveedores.size(); i++) {
            Proveedor actual = listaProveedores.get(i);
            if (actual != null
                    && buscada.equalsIgnoreCase(normalizar(actual.getIdentificacion()))) {
                listaProveedores.set(i, proveedor);
                return true;
            }
        }
        return false;
    }

    public boolean eliminarProveedor(String identificacion) {
        String buscada = normalizar(identificacion);
        if (buscada == null) {
            return false;
        }

        for (int i = 0; i < listaProveedores.size(); i++) {
            Proveedor actual = listaProveedores.get(i);
            if (actual != null
                    && buscada.equalsIgnoreCase(normalizar(actual.getIdentificacion()))) {
                listaProveedores.remove(i);
                return true;
            }
        }
        return false;
    }

    private String normalizar(String valor) {
        if (valor == null || valor.trim().isEmpty()) {
            return null;
        }
        return valor.trim();
    }
}
