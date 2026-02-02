/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.espe.dao;

import com.espe.interfaces.ICrud;
import com.espe.modelo.Matricula;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;

/**
 *
 * @author Paul
 */
public class MatriculaDAO  implements ICrud<Matricula>  {
    
    private final MongoCollection<Document> coleccion;

    public MatriculaDAO() {
        MongoDatabase db = ConexionMongo.getInstancia().getDatabase();
        this.coleccion = db.getCollection("matriculas");
    }


    @Override
    public boolean crear(Matricula m) {
        try {
            Document doc = new Document("idMatricula", m.getIdMatricula())
                    .append("fecha", m.getFecha())
                    .append("cedulaEstudiante", m.getCedulaEstudiante())
                    .append("codigosAsignaturas", m.getCodigosAsignaturas());
            coleccion.insertOne(doc);
            return true;
        } catch (Exception e) {
            System.err.println("Error al matricular: " + e.getMessage());
            return false;
        }
    }
    public List<Matricula> listar() {
        List<Matricula> lista = new ArrayList<>();
        return lista;
    }

    @Override
    public List<Matricula> leerTodos() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }


    @Override
    public boolean eliminar(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Matricula buscarPorId(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean actualizar(Matricula objeto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}

