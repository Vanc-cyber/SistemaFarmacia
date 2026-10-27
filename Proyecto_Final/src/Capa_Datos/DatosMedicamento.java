
package Capa_Datos;

import Capa_Entidades.Medicamento;
import java.util.ArrayList;

public class DatosMedicamento {

    // Lista donde se almacenan los medicamentos
    private final ArrayList<Medicamento> listaMedicamentos = new ArrayList<>();

    // Agrega un medicamento a la lista
    public void agregarMedicamento(Medicamento medicamento) {
        if (medicamento == null) {
            throw new IllegalArgumentException("El medicamento no puede ser null.");
        }
        listaMedicamentos.add(medicamento);
    }

    // Busca un medicamento por su ID
    public Medicamento buscarMedicamento(int idMedicamento) {
        for (Medicamento medicamento : listaMedicamentos) {
            if (medicamento != null
                    && medicamento.getIdMedicamento() == idMedicamento) {
                return medicamento;
            }
        }
        return null;
    }

    // Devuelve los medicamentos registrados
    public ArrayList<Medicamento> listarMedicamentos() {
        return new ArrayList<>(listaMedicamentos);
    }

    // Modifica un medicamento existente
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

    // Elimina un medicamento por su ID
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
