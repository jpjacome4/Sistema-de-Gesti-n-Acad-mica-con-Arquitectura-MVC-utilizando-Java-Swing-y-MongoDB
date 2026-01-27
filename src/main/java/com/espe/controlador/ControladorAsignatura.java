/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.espe.controlador;

import com.espe.dao.AsignaturaDAO;
import com.espe.dao.UsuarioDAO;
import com.espe.modelo.Asignatura;
import com.espe.modelo.Docente;
import com.espe.modelo.Usuario;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Paul
 */
public class ControladorAsignatura {
    
    private UsuarioDAO usuarioDAO;
    private AsignaturaDAO asignaturaDAO;

    public ControladorAsignatura() {
        this.usuarioDAO = usuarioDAO;
        this.asignaturaDAO = asignaturaDAO;
    }
    
    public List<Docente> obtenerListaDocentes() {
        List<Docente> docentes = new ArrayList<>();

        List<Usuario> usuarios = usuarioDAO.leerTodos();

        for (Usuario u : usuarios) {
            if (u instanceof Docente) {
                docentes.add((Docente) u);
            }
        }
        return docentes;
    }
    
    
    public boolean guardarAsignatura(String codigo, String nombre,
                                     int creditos, String cedulaDocente) {

        if (creditos <= 0) {
            throw new IllegalArgumentException("Los créditos deben ser positivos");
        }

        Asignatura a = new Asignatura(codigo, nombre, creditos, cedulaDocente);
        return asignaturaDAO.crear(a);
    }
}
