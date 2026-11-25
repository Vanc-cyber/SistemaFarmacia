package Capa_Interfaz;

import Capa_Interfaz.Vistas.VentanaPrincipal;
import javax.swing.SwingUtilities;

public class SistemaGestionFarmacia {

    public static void main(String[] args) {
        //Invocamos a la capa de Ventanas//
        SwingUtilities.invokeLater(() -> {
            VentanaPrincipal ventana = new VentanaPrincipal();
            ventana.setLocationRelativeTo(null);
            ventana.setVisible(true);
        });
    }
}