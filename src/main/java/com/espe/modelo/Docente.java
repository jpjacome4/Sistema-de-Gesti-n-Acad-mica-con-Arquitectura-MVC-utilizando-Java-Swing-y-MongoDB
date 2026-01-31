/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.espe.modelo;

/**
 *
 * @author Paul
 */
public class Docente extends Usuario{
    public String titulo;
    public String departamento;
     
    public Docente(String cedula, String nombre, String apellido, String correo, String titulo, String departamento) {
        super(cedula, nombre, apellido, correo);
        this.titulo = titulo;
        this.departamento = departamento;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }
    

    @Override
    public String toString() {
        return super.toString() + ", titulo=" + titulo + ", Departamento=" + departamento + '}';
    }
    
}
