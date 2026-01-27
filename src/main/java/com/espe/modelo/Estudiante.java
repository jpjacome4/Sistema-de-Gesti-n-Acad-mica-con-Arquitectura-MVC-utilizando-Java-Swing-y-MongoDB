/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.espe.modelo;

/**
 *
 * @author Paul
 */
public class Estudiante extends Usuario{
    private String carrera;
    
    public Estudiante(String cedula, String nombre, String apellido, String correo, String carrera) {
        super(cedula, nombre, apellido, correo);
        this.carrera = carrera;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    @Override
    public String toString() {
        // Reutilizamos el toString() de Usuario y le sumamos la carrera
        return super.toString() + ", carrera=" + carrera + '}';
    }
}
   
    
