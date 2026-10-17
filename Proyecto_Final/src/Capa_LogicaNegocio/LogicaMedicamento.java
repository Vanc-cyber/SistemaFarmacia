package Capa_LogicaNegocio;

import Capa_Datos.DatosMedicamento;
import Capa_Entidades.Medicamento;
import java.util.ArrayList;

/** Valida los datos y coordina las operaciones de medicamentos. */
public class LogicaMedicamento {

    private final DatosMedicamento datosMedicamento;

    public LogicaMedicamento() {
        this(new DatosMedicamento());
    }

    public LogicaMedicamento(DatosMedicamento datosMedicamento) {
        if (datosMedicamento == null) {
            throw new IllegalArgumentException("DatosMedicamento es obligatorio.");
        }
        this.datosMedicamento = datosMedicamento;
    }

    public void registrarMedicamento(Medicamento medicamento) {
        if (medicamento == null) {
            throw new IllegalArgumentException("Debe indicar un medicamento.");
        }

        requerido(medicamento.getNombre(), "El nombre del medicamento es obligatorio.");

        if (medicamento.getIdMedicamento() <= 0) {
            throw new IllegalArgumentException("El identificador debe ser mayor que cero.");
        }
        if (medicamento.getPrecio() <= 0
                || Double.isNaN(medicamento.getPrecio())
                || Double.isInfinite(medicamento.getPrecio())) {
            throw new IllegalArgumentException("El precio debe ser un número mayor que cero.");
        }
        if (medicamento.getCantidadDisponible() < 0) {
            throw new IllegalArgumentException(
                    "La cantidad disponible no puede ser negativa.");
        }
        if (medicamento.getFechaVencimiento() == null) {
            throw new IllegalArgumentException("Debe indicar la fecha de vencimiento.");
        }
        if (medicamento.getCategoria() == null) {
            throw new IllegalArgumentException("Debe asignar una categoría.");
        }
        if (medicamento.getProveedor() == null) {
            throw new IllegalArgumentException("Debe asignar un proveedor.");
        }

        medicamento.setNombre(medicamento.getNombre().trim());

        if (datosMedicamento.buscarMedicamento(medicamento.getIdMedicamento()) != null) {
            throw new IllegalArgumentException(
                    "Ya existe un medicamento con ese identificador.");
        }

        datosMedicamento.agregarMedicamento(medicamento);
    }

    public Medicamento buscarMedicamento(int idMedicamento) {
        if (idMedicamento <= 0) {
            throw new IllegalArgumentException("El identificador debe ser mayor que cero.");
        }
        return datosMedicamento.buscarMedicamento(idMedicamento);
    }

    public ArrayList<Medicamento> listarMedicamentos() {
        return datosMedicamento.listarMedicamentos();
    }

    private void requerido(String valor, String mensaje) {
        if (valor == null || valor.trim().isEmpty()) {
            throw new IllegalArgumentException(mensaje);
        }
    }
}
