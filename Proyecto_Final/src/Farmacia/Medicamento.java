
package Farmacia;

public class Medicamento {

    private String nombre;
    private double precio;
    private Categoria categoria;
    private Proveedor proveedor;

    // Constructor
    public Medicamento(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    // Obtener el nombre
    public String getNombre() {
        return nombre;
    }

    // Cambiar el nombre
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Obtener el precio
    public double getPrecio() {
        return precio;
    }

    // Cambiar el precio
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    // Obtener la categoria
    public Categoria getCategoria() {
        return categoria;
    }

    // Asignar la categoria
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    // Obtener el proveedor
    public Proveedor getProveedor() {
        return proveedor;
    }

    // Asignar el proveedor
    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }
}