package Capa_Datos;

import Capa_Entidades.Medicamento;
import java.util.ArrayList;

/**
 * Almacena y consulta medicamentos en memoria.
 * Las reglas de negocio se validan en LogicaMedicamento.
 */
public class DatosMedicamento {

    private final ArrayList<Medicamento> listaMedicamentos = new ArrayList<>();

    public void agregarMedicamento(Medicamento medicamento) {
        if (medicamento == null) {
            throw new IllegalArgumentException("El medicamento no puede ser null.");
        }
        listaMedicamentos.add(medicamento);
    }

    public Medicamento buscarMedicamento(int idMedicamento) {
        for (Medicamento medicamento : listaMedicamentos) {
            if (medicamento != null
                    && medicamento.getIdMedicamento() == idMedicamento) {
                return medicamento;
            }
        }
        return null;
    }

    public ArrayList<Medicamento> listarMedicamentos() {
        return new ArrayList<>(listaMedicamentos);
    }

    public boolean modificarMedicamento(Medicamento medicamento) {
        if (medicamento == null) {
            return false;
        }

        for (int i = 0; i < listaMedicamentos.size(); i++) {
            Medicamento actual = listaMedicamentos.get(i);
            if (actual != null
                    && actual.getIdMedicamento() == medicamento.getIdMedicamento()) {
                listaMedicamentos.set(i, medicamento);
                return true;
            }
        }
        return false;
    }

    public boolean eliminarMedicamento(int idMedicamento) {
        for (int i = 0; i < listaMedicamentos.size(); i++) {
            Medicamento actual = listaMedicamentos.get(i);
            if (actual != null && actual.getIdMedicamento() == idMedicamento) {
                listaMedicamentos.remove(i);
                return true;
            }
        }
        return false;
    }
}
