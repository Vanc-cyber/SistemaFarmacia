package Capa_LogicaNegocio;

import Capa_Datos.DatosVenta;
import Capa_Entidades.DetalleVenta;
import Capa_Entidades.Venta;
import java.util.ArrayList;

/** Valida las ventas y coordina su almacenamiento. */
public class LogicaVenta {

    private final DatosVenta datosVenta;

    public LogicaVenta() {
        this(new DatosVenta());
    }

    public LogicaVenta(DatosVenta datosVenta) {
        if (datosVenta == null) {
            throw new IllegalArgumentException("DatosVenta es obligatorio.");
        }
        this.datosVenta = datosVenta;
    }

    public void registrarVenta(Venta venta) {
        if (venta == null) {
            throw new IllegalArgumentException("Debe indicar una venta.");
        }
        if (venta.getNumeroVenta() <= 0) {
            throw new IllegalArgumentException(
                    "El número de venta debe ser mayor que cero.");
        }
        if (venta.getFecha() == null) {
            throw new IllegalArgumentException("La fecha de venta es obligatoria.");
        }
        if (venta.getCliente() == null) {
            throw new IllegalArgumentException("Debe asociar un cliente a la venta.");
        }
        if (venta.getDetalles() == null || venta.getDetalles().isEmpty()) {
            throw new IllegalArgumentException(
                    "La venta debe contener al menos un detalle.");
        }

        for (DetalleVenta detalle : venta.getDetalles()) {
            if (detalle == null) {
                throw new IllegalArgumentException("La venta contiene un detalle vacío.");
            }
            if (detalle.getMedicamento() == null) {
                throw new IllegalArgumentException(
                        "Cada detalle debe tener un medicamento.");
            }
            if (detalle.getCantidad() <= 0) {
                throw new IllegalArgumentException(
                        "La cantidad de cada medicamento debe ser mayor que cero.");
            }
            double precio = detalle.getPrecioUnitario();
            if (precio <= 0 || Double.isNaN(precio) || Double.isInfinite(precio)) {
                throw new IllegalArgumentException(
                        "El precio unitario debe ser un número mayor que cero.");
            }
        }

        if (datosVenta.buscarVenta(venta.getNumeroVenta()) != null) {
            throw new IllegalArgumentException(
                    "Ya existe una venta con ese número.");
        }

        // Los subtotales y el total se calculan desde los detalles antes de guardar.
        for (DetalleVenta detalle : venta.getDetalles()) {
            detalle.calcularSubtotal();
        }
        venta.calcularTotal();
        datosVenta.agregarVenta(venta);
    }

    public Venta buscarVenta(int numeroVenta) {
        if (numeroVenta <= 0) {
            throw new IllegalArgumentException(
                    "El número de venta debe ser mayor que cero.");
        }
        return datosVenta.buscarVenta(numeroVenta);
    }

    public ArrayList<Venta> listarVentas() {
        return datosVenta.listarVentas();
    }
}
