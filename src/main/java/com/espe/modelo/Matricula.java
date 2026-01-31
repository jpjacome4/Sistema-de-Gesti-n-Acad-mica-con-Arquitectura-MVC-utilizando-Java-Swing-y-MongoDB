/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.espe.modelo;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
/**
 *
 * @author Paul
 */
public class Matricula {
    private String idMatricula;
    private Date fecha;
    private String cedulaEstudiante;
    private List<String> codigosAsignaturas;

    public Matricula() {
        this.idMatricula = UUID.randomUUID().toString().substring(0, 8);
        this.fecha = new Date();
        this.codigosAsignaturas = new ArrayList<>();
    }

    public String getIdMatricula() {
        return idMatricula;
    }

    public void setIdMatricula(String idMatricula) {
        this.idMatricula = idMatricula;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getCedulaEstudiante() {
        return cedulaEstudiante;
    }

    public void setCedulaEstudiante(String cedulaEstudiante) {
        this.cedulaEstudiante = cedulaEstudiante;
    }

    public List<String> getCodigosAsignaturas() {
        return codigosAsignaturas;
    }

    public void setCodigosAsignaturas(List<String> codigosAsignaturas) {
        this.codigosAsignaturas = codigosAsignaturas;
    }



    public int calcularTotalCreditos(List<Asignatura> listaAsignaturas) {
    int total = 0;
    for (Asignatura a : listaAsignaturas) {
        total = total + a.getCreditos();
    }
    return total;
}
}


