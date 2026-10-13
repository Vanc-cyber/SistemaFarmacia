
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyecto_final;

import java.util.ArrayList;

public class SistemaGestionFamacia {

    public static void main(String[] args) {

        // Crear la lista de medicamentos
ArrayList<Farmacia.Medicamento> medicamentos = new ArrayList<>();

medicamentos.add(new Farmacia.Medicamento("Paracetamol", 1500.0));
medicamentos.add(new Farmacia.Medicamento("Acetaminofen", 2000.0));
medicamentos.add(new Farmacia.Medicamento("Ibuprofeno", 2500.0));
medicamentos.add(new Farmacia.Medicamento("Loratadina", 1800.0));
medicamentos.add(new Farmacia.Medicamento("Omeprazol", 2200.0));
medicamentos.add(new Farmacia.Medicamento("Amoxicilina", 3500.0));
medicamentos.add(new Farmacia.Medicamento("Diclofenaco", 2000.0));
medicamentos.add(new Farmacia.Medicamento("Cetirizina", 1700.0));
medicamentos.add(new Farmacia.Medicamento("Aspirina", 1200.0));
medicamentos.add(new Farmacia.Medicamento("Sales de rehidratacion", 1000.0));
medicamentos.add(new Farmacia.Medicamento("Vitamina C", 2500.0));
medicamentos.add(new Farmacia.Medicamento("Calamina", 2800.0));       
// Crear una categoria
        Farmacia.Categoria categoria1 =
                new Farmacia.Categoria(
                        "Analgesicos",
                        "Medicamentos para aliviar el dolor"
                );

        // Relacionar el primer medicamento con su categoria
        medicamentos.get(0).setCategoria(categoria1);

        // Mostrar los medicamentos
        System.out.println("===== MEDICAMENTOS =====");

        for (Farmacia.Medicamento m : medicamentos) {
            System.out.println("Medicamento: " + m.getNombre());
            System.out.println("Precio: " + m.getPrecio());

            if (m.getCategoria() != null) {
                System.out.println("Categoria: "
                        + m.getCategoria().getNombre());
            }

            System.out.println("--------------------");
        }

        // Mostrar los datos de la categoria
        System.out.println("===== CATEGORIA =====");
        System.out.println("Categoria: " + categoria1.getNombre());
        System.out.println("Descripcion: " + categoria1.getDescripcion());
        System.out.println("--------------------");

    
        // Crear un cliente
        Farmacia.Cliente cliente1 =
                new Farmacia.Cliente(
                        "Nailea Montero",
                        "703250849",
                        "72261269"
                );

        // Mostrar los datos del cliente
        System.out.println("===== CLIENTE =====");
        cliente1.mostrarDatos();
        System.out.println("--------------------");


        // Crear un proveedor
        Farmacia.Proveedor proveedor1 =
                new Farmacia.Proveedor(
                        "Eylin Morera",
                        "Distribuidora Medica CR",
                        "71882119"
                );

        // Asignar el proveedor a los 12 medicamentos
for (Farmacia.Medicamento m : medicamentos) {
    m.setProveedor(proveedor1);
}

        // Mostrar los datos del proveedor
        System.out.println("===== PROVEEDOR =====");
        proveedor1.mostrarDatos();
        System.out.println("--------------------");


// Mostrar los medicamentos y sus proveedores
System.out.println("===== INVENTARIO DE FARMACIA =====");

for (Farmacia.Medicamento m : medicamentos) {
    System.out.println("Medicamento: " + m.getNombre());
    System.out.println("Precio: " + m.getPrecio());
    System.out.println("Proveedor: " + m.getProveedor().getNombre());
    System.out.println("--------------------");
}
        // Mostrar los datos del proveedor
        System.out.println("===== PROVEEDOR =====");
        System.out.println("Proveedor: " + proveedor1.getNombre());
        System.out.println("Empresa: " + proveedor1.getEmpresa());
        System.out.println("Telefono: " + proveedor1.getTelefono());
        System.out.println("--------------------");
    }
}