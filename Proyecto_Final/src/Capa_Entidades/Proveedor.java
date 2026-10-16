
package Capa_Entidades;


import java.util.Date;

public class Proveedor extends Persona {

    //Atributos//
    private String empresa;

    /*Contructor*/

    public Proveedor(String empresa, String identificacion, String nombre, String apellido1, String apellido2, Date fechaNacimiento, String direccion, String correoElectronico, String telefono) {
        super(identificacion, nombre, apellido1, apellido2, fechaNacimiento, direccion, correoElectronico, telefono);
        this.empresa = empresa;
    }
  
    /*Constructor vacío*/
    public Proveedor() {
        super();
        this.empresa = "";

    }

    /*Metodos getter y setter*/
    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }
}
