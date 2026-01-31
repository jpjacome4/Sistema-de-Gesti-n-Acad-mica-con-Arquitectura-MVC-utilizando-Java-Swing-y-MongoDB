/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.espe.modelo;

/**
 *
 * @author Paul
 */
public class UsuarioFactory {
    public static Usuario crearUsuario(String tipo) {
        
        if (tipo == null) {
            return null;
        }
        if (tipo.equalsIgnoreCase("EST")) {
            
            return new Estudiante("", "", "", "", "");
        } 
        
        else if (tipo.equalsIgnoreCase("DOC")) {
           
            return new Docente("", "", "", "", "", "");
        }
        return null; 
    }
}
