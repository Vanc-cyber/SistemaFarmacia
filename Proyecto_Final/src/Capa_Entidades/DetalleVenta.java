
package Capa_Entidades;


public class DetalleVenta {
       /*Atributos*/
    private Medicamento medicamento;
    private int cantidad;
    private  double precioUnitario;
    private double subTotal;
    
    
    /*Constructor*/

    public DetalleVenta(Medicamento medicamento, int cantidad, double precioUnitario, double subTotal) {
        this.medicamento = medicamento;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.subTotal = subTotal;
    }
    /*Constructor vacio*/
      public DetalleVenta() {
        this.medicamento = null;
        this.cantidad = 0;
        this.precioUnitario = 0.0;
        this.subTotal =0.0;
    }
      
      /*Metodos getter y setter*/

    public Medicamento getMedicamento() {
        return medicamento;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setMedicamento(Medicamento medicamento) {
        this.medicamento = medicamento;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }
      
    /*Este metodo me permite calcular el subtotal de la venta*/
    public double calcularSubtotal() {
    this.subTotal = this.cantidad * this.precioUnitario;
    return this.subTotal;
}
}
