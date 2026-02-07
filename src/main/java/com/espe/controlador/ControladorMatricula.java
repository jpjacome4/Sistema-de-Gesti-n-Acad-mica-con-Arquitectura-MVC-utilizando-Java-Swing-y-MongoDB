package com.espe.controlador;

import com.espe.dao.MatriculaDAO;
import com.espe.modelo.Matricula;
import java.util.Arrays;
import java.util.List;
import javax.swing.JOptionPane;

public class ControladorMatricula {
    private MatriculaDAO matriculaDAO;

    public ControladorMatricula() {
        this.matriculaDAO = new MatriculaDAO();
    }

    public void registrarMatricula(String cedula, String asignaturasTexto) {
        // Validación básica
        if (cedula.isEmpty() || asignaturasTexto.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Error: Debe ingresar la cédula y al menos una asignatura.");
            return;
        }

        try {
            // Creamos el objeto Matricula (El constructor genera ID y Fecha automáticamente)
            Matricula nuevaMatricula = new Matricula();
            nuevaMatricula.setCedulaEstudiante(cedula);
            
            // Convertimos el texto (ej: "MAT-1, PROG-2") en una lista de Strings
            List<String> listaCodigos = Arrays.asList(asignaturasTexto.split("\\s*,\\s*"));
            nuevaMatricula.setCodigosAsignaturas(listaCodigos);

            // Intentamos guardar a través del DAO
            boolean exito = matriculaDAO.crear(nuevaMatricula);

            if (exito) {
                JOptionPane.showMessageDialog(null, "¡Matrícula registrada correctamente!\nID: " + nuevaMatricula.getIdMatricula());
            } else {
                JOptionPane.showMessageDialog(null, "Hubo un error al guardar en la base de datos.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ocurrió un error inesperado: " + e.getMessage());
        }
    }
}