/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.espe.controlador;

import com.espe.dao.MatriculaDAO;
import com.espe.dao.UsuarioDAO;
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

    /**
     * Obtiene la lista de estudiantes inscritos en una materia específica.
     * @param codigoMateria El código de la asignatura (ID buscado con buscarPorId).
     * @return Lista de objetos Estudiante con sus nombres y datos reales.
     */
    public List<Estudiante> obtenerEstudiantesPorMateria(String codigoMateria) {
        List<Estudiante> estudiantesInscritos = new ArrayList<>();

        // 1. Obtiene todas las matrículas del sistema
        List<Matricula> todasLasMatriculas = mDAO.leerTodos();

        for (Matricula matricula : todasLasMatriculas) {
            // Verificamos si la matrícula contiene el código de la materia
            if (matricula.getCodigosAsignaturas().contains(codigoMateria)) {
                
                // 2. Extraemos la cédula del estudiante de esa matrícula
                String cedulaMatricula = matricula.getCedulaEstudiante();

                // 3. Llamamos a UsuarioDAO para buscar el usuario por su cédula
                // Se obtiene la lista completa de usuarios para realizar la comparación manual
                List<Usuario> listaUsuarios = uDAO.leerTodos();

                for (Usuario usuario : listaUsuarios) {
                    // 4. Comparamos que tengan la misma cédula y sea un estudiante
                    if (usuario.getCedula().equals(cedulaMatricula) && usuario instanceof Estudiante) {
                        
                        // 5. Al coincidir, ya tenemos acceso a los nombres y apellidos
                        estudiantesInscritos.add((Estudiante) usuario);
                        break; // Pasamos a la siguiente matrícula
                    }
                }
            }
        }

        return estudiantesInscritos;
    }
    }

