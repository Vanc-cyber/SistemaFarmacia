package Capa_Entidades;
import java.util.Date;
public class Cliente extends Persona {
 //Atributos//
    private int puntos;

    /*Metodo constructor*/

    public Cliente(int puntos, String identificacion, String nombre, String apellido1, String apellido2, Date fechaNacimiento, String direccion, String correoElectronico, String telefono) {
        super(identificacion, nombre, apellido1, apellido2, fechaNacimiento, direccion, correoElectronico, telefono);
        this.puntos = puntos;
    }
  

    /*Constructor vacio*/
    public Cliente() {
        super();
        this.puntos = 0;
    }

    /*Metodos getter y setter*/

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }
}
