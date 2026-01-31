/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.espe.controlador;

import com.espe.dao.AsignaturaDAO;
import com.espe.dao.MatriculaDAO;
import com.espe.dao.UsuarioDAO;
import com.espe.modelo.Asignatura;
import com.espe.modelo.Matricula;
import com.espe.modelo.Usuario;


/**
 *
 * @author Paul
 */
public class ReporteService {
 private MatriculaDAO mDAO = new MatriculaDAO();
    private UsuarioDAO uDAO = new UsuarioDAO();
    private AsignaturaDAO aDAO = new AsignaturaDAO();

    /*
     * @param idMatricula
     * @return 
     */
    public String generarComprobanteMatricula(String idMatricula) {
        // 1. Buscar la matrícula (Tu DAO)
        // (Asumiendo que implementaste buscar en MatriculaDAO)
        Matricula m = mDAO.buscarPorId(idMatricula); 
        if (m == null) return "Matrícula no encontrada.";

        // 2. Buscar datos del Estudiante (Usa el DAO de Persona 1)
        Usuario est = uDAO.buscarPorCedula(m.getCedulaEstudiante());
        String nombreEst = (est != null) ? est.getNombre() + " " + est.getApellido() : "Desconocido";

        // 3. Construir el reporte
        StringBuilder reporte = new StringBuilder();
        reporte.append("=== COMPROBANTE DE MATRÍCULA ===\n");
        reporte.append("ID: ").append(m.getIdMatricula()).append("\n");
        reporte.append("Estudiante: ").append(nombreEst).append("\n");
        reporte.append("Materias Inscritas:\n");

        // 4. Buscar nombres de materias (Usa el DAO de Persona 2)
        for (String codigo : m.getCodigosAsignaturas()) {
            Asignatura asig = aDAO.buscarPorCodigo(codigo);
            if (asig != null) {
                reporte.append("- ").append(asig.getNombre())
                       .append(" (").append(asig.getCreditos()).append(" cred.)\n");
            }
        }
        
        return reporte.toString();
    }
}
