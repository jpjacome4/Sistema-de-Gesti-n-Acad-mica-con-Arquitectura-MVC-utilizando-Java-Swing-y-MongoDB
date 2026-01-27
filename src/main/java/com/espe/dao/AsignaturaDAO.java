/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.espe.dao;

import com.espe.interfaces.ICrud;
import com.espe.modelo.Asignatura;
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
public class AsignaturaDAO implements ICrud<Asignatura>{

    private MongoCollection<Document> coleccion;
    
    public AsignaturaDAO() {
        MongoDatabase database = ConexionMongo.getInstancia().getDatabase();
        this.coleccion = database.getCollection("asignaturas");
    }
    
    //CREAR
    @Override
    public boolean crear(Asignatura a) {
        try {
            Document doc = new Document("codigo", a.getCodigo())
                    .append("nombre", a.getNombre())
                    .append("creditos", a.getCreditos())
                    .append("cedulaDocente", a.getCedulaDocente());

            coleccion.insertOne(doc);
            return true;
        } catch (Exception e) {
            System.err.println("Error al crear asignatura: " + e.getMessage());
            return false;
        }
    }
    // LEER TODO
    @Override
    public List<Asignatura> leerTodos() {
        List<Asignatura> lista = new ArrayList<>();

        for (Document doc : coleccion.find()) {
            Asignatura a = new Asignatura();
            a.setCodigo(doc.getString("codigo"));
            a.setNombre(doc.getString("nombre"));
            a.setCreditos(doc.getInteger("creditos"));
            a.setCedulaDocente(doc.getString("cedulaDocente"));

            lista.add(a);
        }
        return lista;
    }
    // ACTUALIZAR
    @Override
    public boolean actualizar(Asignatura a) {
        try {
            coleccion.updateOne(
                    Filters.eq("codigo", a.getCodigo()),
                    Updates.combine(
                            Updates.set("nombre", a.getNombre()),
                            Updates.set("creditos", a.getCreditos()),
                            Updates.set("cedulaDocente", a.getCedulaDocente())
                    )
            );
            return true;
        } catch (Exception e) {
            System.err.println("Error al actualizar asignatura: " + e.getMessage());
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
            System.err.println("Error al eliminar asignatura: " + e.getMessage());
            return false;
        }
    }
    //LEER UNO
    @Override
    public Asignatura buscarPorId(String codigo) {
        Document doc = coleccion.find(Filters.eq("codigo", codigo)).first();

        if (doc == null) {
            return null;
        }

        Asignatura a = new Asignatura();
        a.setCodigo(doc.getString("codigo"));
        a.setNombre(doc.getString("nombre"));
        a.setCreditos(doc.getInteger("creditos"));
        a.setCedulaDocente(doc.getString("cedulaDocente"));

        return a;
    }
    
    public List<Asignatura> buscarPorDocente(String cedulaDocente) {
        List<Asignatura> lista = new ArrayList<>();

        for (Document doc : coleccion.find(Filters.eq("cedulaDocente", cedulaDocente))) {
            Asignatura a = new Asignatura();
            a.setCodigo(doc.getString("codigo"));
            a.setNombre(doc.getString("nombre"));
            a.setCreditos(doc.getInteger("creditos"));
            a.setCedulaDocente(doc.getString("cedulaDocente"));

            lista.add(a);
        }
        return lista;
    }
}
