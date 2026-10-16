
package Capa_Entidades;
import java.util.Date;


public class Medicamento {

      //Atributos//
    private int idMedicamento;
    private String nombre;
    private String descripcion;
    private double precio;
    private int cantidadDisponible;
    private Date fechaVencimiento;
    private Categoria categoria;
    private Proveedor proveedor;

    /*Constructor*/
    public Medicamento(int idMedicamento, String nombre, String descripcion, double precio, int cantidadDisponible, Date fechaVencimiento, Categoria categoria, Proveedor proveedor) {
        this.idMedicamento = idMedicamento;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.cantidadDisponible = cantidadDisponible;
        this.fechaVencimiento = fechaVencimiento;
        this.categoria = categoria;
        this.proveedor = proveedor;
    }

    /*Constructor vacío*/
    public Medicamento() {
        this.idMedicamento = 0;
        this.nombre = "";
        this.descripcion = "";
        this.precio = 0.0;
        this.cantidadDisponible = 0;
        this.fechaVencimiento = null;
        this.categoria = null;
        this.proveedor = null;
    }

    /*Metodos setter y getter*/
    public int getIdMedicamento() {
        return idMedicamento;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public int getCantidadDisponible() {
        return cantidadDisponible;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setIdMedicamento(int idMedicamento) {
        this.idMedicamento = idMedicamento;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void setCantidadDisponible(int cantidadDisponible) {
        this.cantidadDisponible = cantidadDisponible;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }
}