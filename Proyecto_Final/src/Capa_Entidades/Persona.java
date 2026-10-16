package Capa_Entidades;

import java.util.Date;

public class Persona {
    //Atributos 

    private String identificacion;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private Date fechaNacimiento;
    private String direccion;
    private String correoElectronico;
    private String telefono;
//Constructor //

    public Persona(String identificacion, String nombre, String apellido1, String apellido2, Date fechaNacimiento, String direccion, String correoElectronico, String telefono) {
        this.identificacion = identificacion;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.fechaNacimiento = fechaNacimiento;
        this.direccion = direccion;
        this.correoElectronico = correoElectronico;
        this.telefono = telefono;
    }

    //Constructor vacío//
    public Persona() {
        this.identificacion = "";
        this.nombre = "";
        this.apellido1 = "";
        this.apellido2 = "";
        this.fechaNacimiento = null;
        this.direccion = "";
        this.correoElectronico = "";
        this.telefono = "";
    }

    //Métodos getter y setter para los atributos//
    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public String getTelefono() {
        return telefono;
    }

}
