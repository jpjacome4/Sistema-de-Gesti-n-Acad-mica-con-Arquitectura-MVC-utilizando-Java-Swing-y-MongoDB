/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.espe.modelo;

/**
 *
 * @author Paul
 */
public class Asignatura {
    //Atributos
    private String codigo;
    private String nombre;
    private int creditos;
    private String cedulaDocente;

    public Asignatura() {
    }

    public Asignatura(String codigo, String nombre, int creditos, String cedulaDocente) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.creditos = creditos;
        this.cedulaDocente = cedulaDocente;
    }

    //Getters y Setters
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCreditos() {
        return creditos;
    }

    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }

    public String getCedulaDocente() {
        return cedulaDocente;
    }

    public void setCedulaDocente(String cedulaDocente) {
        this.cedulaDocente = cedulaDocente;
    }
    
    @Override
    public String toString() {
        return codigo + " - " + nombre + " (" + creditos + " créditos)";
    }
}
