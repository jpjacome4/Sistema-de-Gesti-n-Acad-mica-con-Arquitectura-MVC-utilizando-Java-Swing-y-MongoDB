/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.espe.dao;

import com.espe.interfaces.ICrud;
import com.espe.modelo.Matricula;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
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


    @Override
    public List<Matricula> leerTodos() {
     List<Matricula> lista = new ArrayList<>();

        for (Document doc : coleccion.find()) {
            Matricula m = new Matricula();
            m.setIdMatricula(doc.getString("id Matricula"));
            m.setFecha(doc.getDate("fecha"));
            m.setCedulaEstudiante(doc.getString("Cedula Estudiante"));
            m.setCodigosAsignaturas(doc.getList("codigosAsignaturas", String.class));
            lista.add(m);
        }
        return lista;
    }


    // ACTUALIZAR
    @Override
    public boolean actualizar(Matricula m) {
        try {
            coleccion.updateOne(
                    Filters.eq("id Matricula", m.getIdMatricula()),
                    Updates.combine(
                            Updates.set("Fecha", m.getFecha()),
                            Updates.set("Cedula Estudiante", m.getCedulaEstudiante()),
                            Updates.set("Codigos Asigunaturas", m.getCodigosAsignaturas())
                    )
            );
            return true;
        } catch (Exception e) {
            System.err.println("Error al actualizar matricular: " + e.getMessage());
            return false;
        }
    }
    //ELIMINAR
    @Override
    public boolean eliminar(String codigo) {
        try {
            coleccion.deleteOne(Filters.eq("codigo", codigo));
            return true;
        } catch (Exception e) {
            System.err.println("Error al eliminar Matricula: " + e.getMessage());
            return false;
        }
    }
    //LEER UNO
    @Override
    public Matricula buscarPorId(String codigo) {
        Document doc = coleccion.find(Filters.eq("codigo", codigo)).first();

        if (doc == null) {
            return null;
        }

        Matricula m = new Matricula();
        m.setIdMatricula(doc.getString("id Matricula"));
            m.setFecha(doc.getDate("fecha"));
            m.setCedulaEstudiante(doc.getString("Cedula Estudiante"));
            m.setCodigosAsignaturas(doc.getList("codigosAsignaturas", String.class));

        return m;
    }
    

}

