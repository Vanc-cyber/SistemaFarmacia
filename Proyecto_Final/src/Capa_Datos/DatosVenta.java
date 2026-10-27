
package Capa_Datos;

import Capa_Entidades.Venta;
import java.util.ArrayList;

public class DatosVenta {

    // Lista donde se almacenan las ventas
    private final ArrayList<Venta> listaVentas = new ArrayList<>();

    // Agrega una venta a la lista
    public void agregarVenta(Venta venta) {

        if (venta == null) {
            throw new IllegalArgumentException(
                    "La venta no puede ser null."
            );
        }

        listaVentas.add(venta);
    }

    // Busca una venta por su número
    public Venta buscarVenta(int numeroVenta) {

        for (Venta venta : listaVentas) {

            if (venta != null
                    && venta.getNumeroVenta() == numeroVenta) {

                return venta;
            }
        }

        return null;
    }

    // Devuelve las ventas registradas
    public ArrayList<Venta> listarVentas() {

        return new ArrayList<>(listaVentas);
    }
}
