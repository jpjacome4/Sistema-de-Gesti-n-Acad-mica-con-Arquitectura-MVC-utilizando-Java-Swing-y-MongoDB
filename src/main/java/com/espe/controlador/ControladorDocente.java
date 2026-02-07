package com.espe.controlador;

import com.espe.dao.UsuarioDAO;
import com.espe.modelo.Docente;
import javax.swing.JOptionPane;

public class ControladorDocente {
    private UsuarioDAO dao;

    public ControladorDocente() {
        this.dao = new UsuarioDAO();
    }

    public void registrarDocente(String cedula, String nombre, String apellido, String correo, String titulo, String departamento) {
        if (cedula.isEmpty() || nombre.isEmpty() || titulo.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor, completa los campos obligatorios.");
            return;
        }

        // Creamos el objeto Docente (esto llama al constructor super de Usuario)
        Docente nuevo = new Docente(cedula, nombre, apellido, correo, titulo, departamento);
        
        try {
            dao.crear(nuevo); // Tu DAO ya maneja la lógica de guardado
            JOptionPane.showMessageDialog(null, "Docente registrado con éxito.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al registrar: " + e.getMessage());
        }
    }
}