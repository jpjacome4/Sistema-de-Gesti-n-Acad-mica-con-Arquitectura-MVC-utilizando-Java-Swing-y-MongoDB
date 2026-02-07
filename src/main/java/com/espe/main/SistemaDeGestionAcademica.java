package com.espe.main;

import com.espe.vista.VistaPrincipal;

public class SistemaDeGestionAcademica {
    public static void main(String[] args) {
        // Ejecutamos la interfaz gráfica en el hilo de eventos de Swing
        java.awt.EventQueue.invokeLater(() -> {
            VistaPrincipal principal = new VistaPrincipal();
            principal.setLocationRelativeTo(null); // Esto centra la ventana en tu pantalla
            principal.setVisible(true);
        });
    }
}