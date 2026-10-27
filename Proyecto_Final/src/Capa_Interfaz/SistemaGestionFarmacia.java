package Capa_Interfaz;

import Capa_Datos.DatosCategoria;
import Capa_Datos.DatosCliente;
import Capa_Datos.DatosMedicamento;
import Capa_Datos.DatosProveedor;
import Capa_Datos.DatosVenta;

import Capa_Entidades.Categoria;
import Capa_Entidades.Cliente;
import Capa_Entidades.DetalleVenta;
import Capa_Entidades.Medicamento;
import Capa_Entidades.Proveedor;
import Capa_Entidades.Venta;

import Capa_LogicaNegocio.LogicaCategoria;
import Capa_LogicaNegocio.LogicaCliente;
import Capa_LogicaNegocio.LogicaMedicamento;
import Capa_LogicaNegocio.LogicaProveedor;
import Capa_LogicaNegocio.LogicaVenta;

import java.util.ArrayList;
import java.util.Date;

public class SistemaGestionFarmacia {

    public static void main(String[] args) {

        try {

            // Crear objetos de datos
            DatosCategoria datosCategoria = new DatosCategoria();
            DatosCliente datosCliente = new DatosCliente();
            DatosProveedor datosProveedor = new DatosProveedor();
            DatosMedicamento datosMedicamento = new DatosMedicamento();
            DatosVenta datosVenta = new DatosVenta();

            // Crear objetos de lógica
            LogicaCategoria logicaCategoria =
                    new LogicaCategoria(datosCategoria);

            LogicaCliente logicaCliente =
                    new LogicaCliente(datosCliente);

            LogicaProveedor logicaProveedor =
                    new LogicaProveedor(datosProveedor);

            LogicaMedicamento logicaMedicamento =
                    new LogicaMedicamento(datosMedicamento);

            LogicaVenta logicaVenta =
                    new LogicaVenta(datosVenta, datosMedicamento);


            // Registrar categoría
            Categoria categoria1 = new Categoria(
                    1,
                    "Analgesicos",
                    "Medicamentos para aliviar el dolor"
            );

            logicaCategoria.registrarCategoria(categoria1);

            System.out.println("=================================");
            System.out.println("       CATEGORIA REGISTRADA");
            System.out.println("=================================");
            System.out.println("ID: " + categoria1.getIdCategoria());
            System.out.println("Nombre: " + categoria1.getNombre());
            System.out.println("Descripcion: " + categoria1.getDescripcion());


            // Registrar proveedor
            Proveedor proveedor1 = new Proveedor(
                    "Distribuidora Medica CR",
                    "1-1111-1111",
                    "Eylin",
                    "Morera",
                    "Vargas",
                    new Date(),
                    "San Jose",
                    "proveedor@farmacia.com",
                    "71882119"
            );

            logicaProveedor.registrarProveedor(proveedor1);

            System.out.println();
            System.out.println("=================================");
            System.out.println("       PROVEEDOR REGISTRADO");
            System.out.println("=================================");
            System.out.println("Nombre: " + proveedor1.getNombre());
            System.out.println("Empresa: " + proveedor1.getEmpresa());


            // Registrar cliente
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

            logicaCliente.registrarCliente(cliente1);

            System.out.println();
            System.out.println("=================================");
            System.out.println("         CLIENTE REGISTRADO");
            System.out.println("=================================");
            System.out.println(
                    "Identificacion: "
                    + cliente1.getIdentificacion()
            );

            System.out.println(
                    "Nombre: "
                    + cliente1.getNombre()
                    + " "
                    + cliente1.getApellido1()
            );


            // Registrar medicamentos
            Medicamento medicamento1 = new Medicamento(
                    1,
                    "Paracetamol",
                    "Medicamento para dolor y fiebre",
                    1500.0,
                    30,
                    new Date(),
                    categoria1,
                    proveedor1
            );

            Medicamento medicamento2 = new Medicamento(
                    2,
                    "Ibuprofeno",
                    "Medicamento antiinflamatorio",
                    2500.0,
                    20,
                    new Date(),
                    categoria1,
                    proveedor1
            );

            logicaMedicamento.registrarMedicamento(medicamento1);
            logicaMedicamento.registrarMedicamento(medicamento2);


            // Mostrar inventario
            System.out.println();
            System.out.println("=================================");
            System.out.println("    INVENTARIO ANTES DE VENTA");
            System.out.println("=================================");

            for (Medicamento medicamento
                    : logicaMedicamento.listarMedicamentos()) {

                System.out.println(
                        "ID: "
                        + medicamento.getIdMedicamento()
                );

                System.out.println(
                        "Medicamento: "
                        + medicamento.getNombre()
                );

                System.out.println(
                        "Precio: "
                        + medicamento.getPrecio()
                        + " colones"
                );

                System.out.println(
                        "Cantidad disponible: "
                        + medicamento.getCantidadDisponible()
                );

                System.out.println("---------------------------------");
            }


            // Modificar cliente
            cliente1.setTelefono("88887777");
            cliente1.setDireccion("Cartago");

            logicaCliente.modificarCliente(cliente1);


            // Modificar proveedor
            proveedor1.setEmpresa(
                    "Distribuidora Farmaceutica CR"
            );

            logicaProveedor.modificarProveedor(proveedor1);


            // Modificar medicamento
            medicamento1.setPrecio(1800.0);

            logicaMedicamento.modificarMedicamento(
                    medicamento1
            );


            // Crear detalle de venta
            DetalleVenta detalle1 =
                    new DetalleVenta(
                            medicamento1,
                            2,
                            medicamento1.getPrecio(),
                            0.0
                    );

            ArrayList<DetalleVenta> detalles =
                    new ArrayList<>();

            detalles.add(detalle1);


            // Registrar venta
            Venta venta1 = new Venta(
                    1,
                    new Date(),
                    cliente1,
                    detalles,
                    0.0
            );

            logicaVenta.registrarVenta(venta1);


            // Mostrar venta
            System.out.println();
            System.out.println("=================================");
            System.out.println("           VENTA REALIZADA");
            System.out.println("=================================");

            System.out.println(
                    "Numero de venta: "
                    + venta1.getNumeroVenta()
            );

            System.out.println(
                    "Cliente: "
                    + venta1.getCliente().getNombre()
            );

            System.out.println(
                    "Medicamento: "
                    + detalle1.getMedicamento().getNombre()
            );

            System.out.println(
                    "Cantidad vendida: "
                    + detalle1.getCantidad()
            );

            System.out.println(
                    "Precio unitario: "
                    + detalle1.getPrecioUnitario()
                    + " colones"
            );

            System.out.println(
                    "Subtotal: "
                    + detalle1.getSubTotal()
                    + " colones"
            );

            System.out.println(
                    "TOTAL: "
                    + venta1.getTotal()
                    + " colones"
            );


            // Consultar inventario actualizado
            Medicamento medicamentoActualizado =
                    logicaMedicamento.buscarMedicamento(1);

            System.out.println();
            System.out.println("=================================");
            System.out.println("   INVENTARIO DESPUES DE VENTA");
            System.out.println("=================================");

            System.out.println(
                    "Medicamento: "
                    + medicamentoActualizado.getNombre()
            );

            System.out.println("Stock inicial: 30");

            System.out.println(
                    "Cantidad vendida: "
                    + detalle1.getCantidad()
            );

            System.out.println(
                    "Stock actual: "
                    + medicamentoActualizado.getCantidadDisponible()
            );


            // Buscar venta
            System.out.println();
            System.out.println("=================================");
            System.out.println("           BUSCAR VENTA");
            System.out.println("=================================");

            Venta ventaEncontrada =
                    logicaVenta.buscarVenta(1);

            if (ventaEncontrada != null) {

                System.out.println(
                        "Venta encontrada: "
                        + ventaEncontrada.getNumeroVenta()
                );

                System.out.println(
                        "Total: "
                        + ventaEncontrada.getTotal()
                        + " colones"
                );
            }


            // Buscar categoría
            System.out.println();
            System.out.println("=================================");
            System.out.println("          BUSCAR CATEGORIA");
            System.out.println("=================================");

            Categoria categoriaEncontrada =
                    logicaCategoria.buscarCategoria(1);

            if (categoriaEncontrada != null) {

                System.out.println(
                        "Categoria encontrada: "
                        + categoriaEncontrada.getNombre()
                );
            }


            // Modificar categoría
            categoria1.setDescripcion(
                    "Medicamentos para aliviar dolor y fiebre"
            );

            logicaCategoria.modificarCategoria(categoria1);


            // Eliminar medicamento
            System.out.println();
            System.out.println("=================================");
            System.out.println("        ELIMINAR MEDICAMENTO");
            System.out.println("=================================");

            boolean medicamentoEliminado =
                    logicaMedicamento.eliminarMedicamento(2);

            if (medicamentoEliminado) {

                System.out.println(
                        "Ibuprofeno eliminado correctamente."
                );
            }


            // Mostrar resumen
            System.out.println();
            System.out.println("=================================");
            System.out.println("           RESUMEN FINAL");
            System.out.println("=================================");

            System.out.println(
                    "Categorias registradas: "
                    + logicaCategoria.listarCategorias().size()
            );

            System.out.println(
                    "Clientes registrados: "
                    + logicaCliente.listarClientes().size()
            );

            System.out.println(
                    "Proveedores registrados: "
                    + logicaProveedor.listarProveedores().size()
            );

            System.out.println(
                    "Medicamentos registrados: "
                    + logicaMedicamento.listarMedicamentos().size()
            );

            System.out.println(
                    "Ventas registradas: "
                    + logicaVenta.listarVentas().size()
            );

        } catch (IllegalArgumentException e) {

            // Mostrar errores de validación
            System.out.println();
            System.out.println(
                    "ERROR: "
                    + e.getMessage()
            );
        }
    }
}