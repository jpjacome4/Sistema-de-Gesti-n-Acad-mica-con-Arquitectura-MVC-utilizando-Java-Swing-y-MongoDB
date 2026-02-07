/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.espe.controlador;

import com.espe.dao.AsignaturaDAO;
import com.espe.dao.MatriculaDAO;
import com.espe.dao.UsuarioDAO;
import com.espe.modelo.Asignatura;
import com.espe.modelo.Estudiante;
import com.espe.modelo.Matricula;
import com.espe.modelo.Usuario;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Paul
 */
public class ReporteService {
   // Dependencias necesarias para la integración de Persona 3
    private MatriculaDAO mDAO = new MatriculaDAO();
    private UsuarioDAO uDAO = new UsuarioDAO();
    private AsignaturaDAO aDAO = new AsignaturaDAO();

    /**
     * Obtiene la lista de estudiantes inscritos en una materia específica.
     * @param codigoMateria El código de la asignatura (ID buscado con buscarPorId).
     * @return Lista de objetos Estudiante con sus nombres y datos reales.
     */
    public List<Estudiante> obtenerEstudiantesPorMateria(String codigoMateria) {
        List<Estudiante> inscritos = new ArrayList<>();
        List<Matricula> matriculas = mDAO.leerTodos();
        List<Usuario> usuarios = uDAO.leerTodos();

        for (Matricula m : matriculas) {
            // Verificamos si la materia está en la lista de la matrícula
            if (m.getCodigosAsignaturas() != null && m.getCodigosAsignaturas().contains(codigoMateria)) {
                for (Usuario u : usuarios) {
                    if (u.getCedula().equals(m.getCedulaEstudiante()) && u instanceof Estudiante) {
                        inscritos.add((Estudiante) u);
                    }
                }
            }
        }
        return inscritos;
    }
    
    public List<Asignatura> obtenerAsignaturasPorDocente(String cedulaDocente) {
        AsignaturaDAO asignaturaDAO = new AsignaturaDAO();
        return asignaturaDAO.buscarPorDocente(cedulaDocente);
    }
}

