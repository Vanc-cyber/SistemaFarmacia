package Capa_Datos;

import Capa_Entidades.Venta;
import java.util.ArrayList;

/**
 * Almacena y consulta ventas en memoria.
 * Las reglas de negocio se validan en LogicaVenta.
 */
public class DatosVenta {

    private final ArrayList<Venta> listaVentas = new ArrayList<>();

    public void agregarVenta(Venta venta) {
        if (venta == null) {
            throw new IllegalArgumentException("La venta no puede ser null.");
        }
        listaVentas.add(venta);
    }

    public Venta buscarVenta(int numeroVenta) {
        for (Venta venta : listaVentas) {
            if (venta != null && venta.getNumeroVenta() == numeroVenta) {
                return venta;
            }
        }
        return null;
    }

    public ArrayList<Venta> listarVentas() {
        return new ArrayList<>(listaVentas);
    }
}
