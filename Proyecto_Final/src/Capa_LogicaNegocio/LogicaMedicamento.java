package Capa_LogicaNegocio;

import Capa_Datos.DatosMedicamento;
import Capa_Entidades.Medicamento;
import java.util.ArrayList;

public class LogicaMedicamento {

    // Acceso a la capa de datos de medicamentos
    private final DatosMedicamento datosMedicamento;

    // Constructor por defecto
    public LogicaMedicamento() {
        this(new DatosMedicamento());
    }

    // Constructor que recibe la capa de datos
    public LogicaMedicamento(DatosMedicamento datosMedicamento) {
        if (datosMedicamento == null) {
            throw new IllegalArgumentException("DatosMedicamento es obligatorio.");
        }
        this.datosMedicamento = datosMedicamento;
    }

    // Registra un medicamento después de validarlo
    public void registrarMedicamento(Medicamento medicamento) {
        if (medicamento == null) {
            throw new IllegalArgumentException("Debe indicar un medicamento.");
        }

        requerido(medicamento.getNombre(), "El nombre del medicamento es obligatorio.");

        if (medicamento.getIdMedicamento() <= 0) {
            throw new IllegalArgumentException("El identificador debe ser mayor que cero.");
        }
        if (medicamento.getPrecio() <= 0) {
            throw new IllegalArgumentException(
                    "El precio debe ser mayor que cero."
            );
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

        // Limpia espacios del nombre
        medicamento.setNombre(medicamento.getNombre().trim());

        if (datosMedicamento.buscarMedicamento(medicamento.getIdMedicamento()) != null) {
            throw new IllegalArgumentException(
                    "Ya existe un medicamento con ese identificador.");
        }

        datosMedicamento.agregarMedicamento(medicamento);
    }

    // Busca un medicamento por su ID
    public Medicamento buscarMedicamento(int idMedicamento) {
        if (idMedicamento <= 0) {
            throw new IllegalArgumentException("El identificador debe ser mayor que cero.");
        }
        return datosMedicamento.buscarMedicamento(idMedicamento);
    }

    // Devuelve los medicamentos registrados
    public ArrayList<Medicamento> listarMedicamentos() {
        return datosMedicamento.listarMedicamentos();
    }

    // Modifica un medicamento existente
    public boolean modificarMedicamento(Medicamento medicamento) {

        if (medicamento == null) {
            throw new IllegalArgumentException(
                    "Debe indicar un medicamento."
            );
        }

        requerido(
                medicamento.getNombre(),
                "El nombre del medicamento es obligatorio."
        );

        if (medicamento.getIdMedicamento() <= 0) {
            throw new IllegalArgumentException(
                    "El identificador debe ser mayor que cero."
            );
        }

        if (medicamento.getPrecio() <= 0
                || Double.isNaN(medicamento.getPrecio())
                || Double.isInfinite(medicamento.getPrecio())) {

            throw new IllegalArgumentException(
                    "El precio debe ser un número mayor que cero."
            );
        }

        if (medicamento.getCantidadDisponible() < 0) {
            throw new IllegalArgumentException(
                    "La cantidad disponible no puede ser negativa."
            );
        }

        if (medicamento.getFechaVencimiento() == null) {
            throw new IllegalArgumentException(
                    "Debe indicar la fecha de vencimiento."
            );
        }

        if (medicamento.getCategoria() == null) {
            throw new IllegalArgumentException(
                    "Debe asignar una categoría."
            );
        }

        if (medicamento.getProveedor() == null) {
            throw new IllegalArgumentException(
                    "Debe asignar un proveedor."
            );
        }

        Medicamento medicamentoExistente
                = datosMedicamento.buscarMedicamento(
                        medicamento.getIdMedicamento()
                );

        if (medicamentoExistente == null) {
            throw new IllegalArgumentException(
                    "El medicamento que desea modificar no existe."
            );
        }

        // Limpia espacios del nombre
        medicamento.setNombre(
                medicamento.getNombre().trim()
        );

        return datosMedicamento.modificarMedicamento(
                medicamento
        );
    }

    // Elimina un medicamento por su ID
    public boolean eliminarMedicamento(int idMedicamento) {

        if (idMedicamento <= 0) {
            throw new IllegalArgumentException(
                    "El identificador debe ser mayor que cero."
            );
        }

        Medicamento medicamento
                = datosMedicamento.buscarMedicamento(
                        idMedicamento
                );

        if (medicamento == null) {
            throw new IllegalArgumentException(
                    "El medicamento que desea eliminar no existe."
            );
        }

        return datosMedicamento.eliminarMedicamento(
                idMedicamento
        );
    }

    // Valida que un texto no sea nulo ni vacío
    private void requerido(String valor, String mensaje) {
        if (valor == null || valor.trim().isEmpty()) {
            throw new IllegalArgumentException(mensaje);
        }
    }
}
