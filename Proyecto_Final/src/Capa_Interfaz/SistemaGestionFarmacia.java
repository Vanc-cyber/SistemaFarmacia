package Capa_Interfaz;

import Capa_Entidades.Categoria;
import Capa_Entidades.Cliente;
import Capa_Entidades.DetalleVenta;
import Capa_Entidades.Medicamento;
import Capa_Entidades.Proveedor;
import Capa_Entidades.Venta;

import java.util.ArrayList;
import java.util.Date;

public class SistemaGestionFarmacia {

    public static void main(String[] args) {

        /* Crear categoría */
        Categoria categoria1 = new Categoria(
                1,
                "Analgesicos",
                "Medicamentos para aliviar el dolor"
        );

        /* Crear proveedor */
        Proveedor proveedor1 = new Proveedor(
                "Distribuidora Medica CR",
                "1-1111-1111",
                "Eylin",
                "Morera",
                "Vargas",
                new Date(),
                "San José",
                "proveedor@farmacia.com",
                "71882119"
        );

        /* Crear medicamento */
        Medicamento medicamento1 = new Medicamento(
                1,
                "Paracetamol",
                "Medicamento para aliviar dolor y fiebre",
                1500.0,
                25,
                new Date(),
                categoria1,
                proveedor1
        );

        /* Crear cliente */
        Cliente cliente1 = new Cliente(
                0,
                "7-0325-0849",
                "Julia",
                "Marin",
                "Mora",
                new Date(),
                "San Jose",
                "julia@email.com",
                "72264565"
        );

        /* Mostrar datos básicos */
        System.out.println("===== MEDICAMENTO =====");
        System.out.println("Nombre: " + medicamento1.getNombre());
        System.out.println("Precio: " + medicamento1.getPrecio());
        System.out.println("Cantidad: " + medicamento1.getCantidadDisponible());
        System.out.println("Categoria: " + medicamento1.getCategoria().getNombre());
        System.out.println("Proveedor: " + medicamento1.getProveedor().getNombre());

        System.out.println("\n===== CLIENTE =====");
        System.out.println("Identificacion: " + cliente1.getIdentificacion());
        System.out.println("Nombre: " + cliente1.getNombre());
        System.out.println("Apellido: " + cliente1.getApellido1());
        System.out.println("Telefono: " + cliente1.getTelefono());

        /* Crear detalle de venta */
        DetalleVenta detalle1 = new DetalleVenta(
                medicamento1,
                2,
                medicamento1.getPrecio(),
                0.0
        );

        detalle1.calcularSubtotal();

        ArrayList<DetalleVenta> detalles = new ArrayList<>();
        detalles.add(detalle1);

        /* Crear venta */
        Venta venta1 = new Venta(
                1,
                new Date(),
                cliente1,
                detalles,
                0.0
        );

        venta1.calcularTotal();

        System.out.println("\n===== VENTA =====");
        System.out.println("Numero de venta: " + venta1.getNumeroVenta());
        System.out.println("Cliente: " + venta1.getCliente().getNombre());
        System.out.println("Medicamento: "
                + detalle1.getMedicamento().getNombre());
        System.out.println("Cantidad: " + detalle1.getCantidad());
        System.out.println("Subtotal: " + detalle1.getSubTotal()+ "   colones");
        System.out.println("Total: " + venta1.getTotal()+ "   colones");
    }
}