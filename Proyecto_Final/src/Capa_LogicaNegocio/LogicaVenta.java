package Capa_LogicaNegocio;

import Capa_Datos.DatosMedicamento;
import Capa_Datos.DatosVenta;
import Capa_Entidades.DetalleVenta;
import Capa_Entidades.Medicamento;
import Capa_Entidades.Venta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LogicaVenta {

    // Acceso a la capa de datos de ventas
    private final DatosVenta datosVenta;

    // Acceso a la capa de datos de medicamentos
    private final DatosMedicamento datosMedicamento;

    // Constructor por defecto
    public LogicaVenta() {
        this(new DatosVenta(), new DatosMedicamento());
    }

    // Constructor que recibe las capas de datos
    public LogicaVenta(
            DatosVenta datosVenta,
            DatosMedicamento datosMedicamento) {

        if (datosVenta == null) {
            throw new IllegalArgumentException(
                    "DatosVenta es obligatorio."
            );
        }

        if (datosMedicamento == null) {
            throw new IllegalArgumentException(
                    "DatosMedicamento es obligatorio."
            );
        }

        this.datosVenta = datosVenta;
        this.datosMedicamento = datosMedicamento;
    }

    // Valida y registra una venta
    public void registrarVenta(Venta venta) {

        if (venta == null) {
            throw new IllegalArgumentException(
                    "Debe indicar una venta."
            );
        }

        if (venta.getNumeroVenta() <= 0) {
            throw new IllegalArgumentException(
                    "El número de venta debe ser mayor que cero."
            );
        }

        if (datosVenta.buscarVenta(venta.getNumeroVenta()) != null) {
            throw new IllegalArgumentException(
                    "Ya existe una venta con ese número."
            );
        }

        if (venta.getFecha() == null) {
            throw new IllegalArgumentException(
                    "La fecha de venta es obligatoria."
            );
        }

        if (venta.getCliente() == null) {
            throw new IllegalArgumentException(
                    "Debe asociar un cliente a la venta."
            );
        }

        if (venta.getDetalles() == null
                || venta.getDetalles().isEmpty()) {

            throw new IllegalArgumentException(
                    "La venta debe contener al menos un detalle."
            );
        }

        // Guarda la cantidad solicitada de cada medicamento
        Map<Integer, Integer> cantidadesSolicitadas
                = new HashMap<>();

        // Valida cada detalle de la venta
        for (DetalleVenta detalle : venta.getDetalles()) {

            if (detalle == null) {
                throw new IllegalArgumentException(
                        "La venta contiene un detalle vacío."
                );
            }

            if (detalle.getMedicamento() == null) {
                throw new IllegalArgumentException(
                        "Cada detalle debe tener un medicamento."
                );
            }

            if (detalle.getCantidad() <= 0) {
                throw new IllegalArgumentException(
                        "La cantidad debe ser mayor que cero."
                );
            }

            double precioUnitario
                    = detalle.getPrecioUnitario();

            if (precioUnitario <= 0) {
                throw new IllegalArgumentException(
                        "El precio unitario debe ser mayor que cero."
                );
            }

            int idMedicamento
                    = detalle.getMedicamento()
                            .getIdMedicamento();

            // Busca el medicamento en el inventario
            Medicamento medicamentoGuardado
                    = datosMedicamento.buscarMedicamento(
                            idMedicamento
                    );

            if (medicamentoGuardado == null) {

                throw new IllegalArgumentException(
                        "El medicamento "
                        + detalle.getMedicamento().getNombre()
                        + " no está registrado."
                );
            }

            // Acumula la cantidad solicitada del medicamento
            int cantidadAcumulada
                    = cantidadesSolicitadas.getOrDefault(
                            idMedicamento,
                            0
                    );

            cantidadAcumulada += detalle.getCantidad();

            cantidadesSolicitadas.put(
                    idMedicamento,
                    cantidadAcumulada
            );

            // Valida que exista suficiente stock
            if (cantidadAcumulada
                    > medicamentoGuardado.getCantidadDisponible()) {

                throw new IllegalArgumentException(
                        "Stock insuficiente para "
                        + medicamentoGuardado.getNombre()
                        + ". Disponible: "
                        + medicamentoGuardado.getCantidadDisponible()
                        + ", solicitado: "
                        + cantidadAcumulada
                );
            }
        }

        // Calcula los subtotales
        for (DetalleVenta detalle : venta.getDetalles()) {

            detalle.calcularSubtotal();
        }

        // Calcula el total de la venta
        venta.calcularTotal();

        // Actualiza el inventario
        for (Map.Entry<Integer, Integer> entrada
                : cantidadesSolicitadas.entrySet()) {

            int idMedicamento = entrada.getKey();
            int cantidadVendida = entrada.getValue();

            Medicamento medicamento
                    = datosMedicamento.buscarMedicamento(
                            idMedicamento
                    );

            int nuevoStock
                    = medicamento.getCantidadDisponible()
                    - cantidadVendida;

            medicamento.setCantidadDisponible(
                    nuevoStock
            );

            // Guarda el nuevo stock del medicamento
            datosMedicamento.modificarMedicamento(
                    medicamento
            );
        }

        // Guarda la venta
        datosVenta.agregarVenta(venta);
    }

    // Busca una venta por su número
    public Venta buscarVenta(int numeroVenta) {

        if (numeroVenta <= 0) {
            throw new IllegalArgumentException(
                    "El número de venta debe ser mayor que cero."
            );
        }

        return datosVenta.buscarVenta(
                numeroVenta
        );
    }

    // Devuelve las ventas registradas
    public ArrayList<Venta> listarVentas() {

        return datosVenta.listarVentas();
    }
}
