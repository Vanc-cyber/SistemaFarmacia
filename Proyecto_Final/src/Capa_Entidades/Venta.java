package Capa_Entidades;

import java.util.Date;
import java.util.ArrayList;

public class Venta {

    /*Atributos*/
    private int numeroVenta;
    private Date fecha;
    private Cliente cliente;
    private ArrayList<DetalleVenta> detalles;
    /*Array de objetos DetalleVenta porque una venta puede incluir
    varios medicamentos*/
    private double total;

    /*Constructor*/
    public Venta(int numeroVenta, Date fecha, Cliente cliente, ArrayList<DetalleVenta> detalles, double total) {
        this.numeroVenta = numeroVenta;
        this.fecha = fecha;
        this.cliente = cliente;
        this.detalles = detalles;
        this.total = total;
    }

    /*Contructor vacio*/
    public Venta() {
        this.numeroVenta = 0;
        this.fecha = null;
        this.cliente = null;
        this.detalles = new ArrayList<>();
        this.total = 0.0;
    }

    /*Metodos getter y setter*/
    public int getNumeroVenta() {
        return numeroVenta;
    }

    public Date getFecha() {
        return fecha;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public ArrayList<DetalleVenta> getDetalles() {
        return detalles;
    }

    public double getTotal() {
        return total;
    }

    public void setNumeroVenta(int numeroVenta) {
        this.numeroVenta = numeroVenta;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setDetalles(ArrayList<DetalleVenta> detalles) {
        this.detalles = detalles;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    /*Metodo que recorre todos los detalles y suma los subtotales*/
    public double calcularTotal() {
        total = 0.0;

        for (DetalleVenta detalle : detalles) {
            total += detalle.calcularSubtotal();
        }

        return total;
    }
}
