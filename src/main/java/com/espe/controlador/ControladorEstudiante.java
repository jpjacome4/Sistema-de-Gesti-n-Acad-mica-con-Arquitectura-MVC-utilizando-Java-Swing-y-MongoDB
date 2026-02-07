package com.espe.controlador;

import com.espe.dao.UsuarioDAO;
import com.espe.modelo.Estudiante;
import javax.swing.JOptionPane;

public class ControladorEstudiante {
    private UsuarioDAO dao;

    public ControladorEstudiante() {
        this.dao = new UsuarioDAO();
    }

    public void registrarEstudiante(String cedula, String nombre, String apellido, String correo, String carrera) {
        // Validación básica
        if (cedula.isEmpty() || nombre.isEmpty() || carrera.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor, completa los campos obligatorios.");
            return;
        }

        // Creamos el objeto Estudiante
        Estudiante nuevo = new Estudiante(cedula, nombre, apellido, correo, carrera);
        
        try {
            dao.crear(nuevo); // El UsuarioDAO que ya tienes guardará en la colección "estudiantes"
            JOptionPane.showMessageDialog(null, "Estudiante registrado con éxito.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al registrar estudiante: " + e.getMessage());
        }
    }
}