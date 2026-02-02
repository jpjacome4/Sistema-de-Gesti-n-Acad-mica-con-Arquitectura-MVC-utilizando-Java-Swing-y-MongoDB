/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.espe.controlador;

import com.espe.dao.AsignaturaDAO;
import com.espe.dao.MatriculaDAO;
import com.espe.dao.UsuarioDAO;
import com.espe.modelo.Estudiante;
import com.espe.modelo.Matricula;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Paul
 */
public class ReporteService {
    // 1. Dependencias: Uso de los tres DAOs requeridos
    private MatriculaDAO mDAO = new MatriculaDAO();
    private UsuarioDAO uDAO = new UsuarioDAO();
    private AsignaturaDAO aDAO = new AsignaturaDAO();

    /**
     * @param codigoMateria
     * @return 
     */
    public List<Estudiante> obtenerEstudiantesPorMateria(String codigoMateria) {
        List<Estudiante> estudiantesInscritos = new ArrayList<>();

                List<Matricula> todasLasMatriculas = mDAO.listar(); 

        for (Matricula matricula : todasLasMatriculas) {
            if (matricula.getCodigosAsignaturas().contains(codigoMateria)) {
                
                String cedula = matricula.getCedulaEstudiante();

                Estudiante estudiante = (Estudiante) uDAO.leerTodos();
                
                if (estudiante != null) {
                    estudiantesInscritos.add(estudiante);
                }
            }
        }

        return estudiantesInscritos;
    }
    }

