
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Farmacia;

public class Cliente extends Persona {

    private String identificacion;

    // Constructor
    public Cliente(String nombre, String identificacion, String telefono) {
        super(nombre, telefono);
        this.identificacion = identificacion;
    }

    // Obtener la identificacion
    public String getIdentificacion() {
        return identificacion;
    }

    // Cambiar la identificacion
    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    // Mostrar los datos del cliente
    @Override
    public void mostrarDatos() {
        System.out.println("Cliente: " + nombre);
        System.out.println("Identificacion: " + identificacion);
        System.out.println("Telefono: " + telefono);
    }
}
