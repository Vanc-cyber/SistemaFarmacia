
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Farmacia;

public class Proveedor extends Persona {

    private String empresa;

    // Constructor
    public Proveedor(String nombre, String empresa, String telefono) {
        super(nombre, telefono);
        this.empresa = empresa;
    }

    // Obtener la empresa
    public String getEmpresa() {
        return empresa;
    }

    // Cambiar la empresa
    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    // Mostrar los datos del proveedor
    @Override
    public void mostrarDatos() {
        System.out.println("Proveedor: " + nombre);
        System.out.println("Empresa: " + empresa);
        System.out.println("Telefono: " + telefono);
    }
}
