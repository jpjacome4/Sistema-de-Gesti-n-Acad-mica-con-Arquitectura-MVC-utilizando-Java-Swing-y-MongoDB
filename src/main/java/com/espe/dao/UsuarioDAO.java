package com.espe.dao;

import com.espe.modelo.Docente;
import com.espe.modelo.Estudiante;
import com.espe.modelo.Usuario;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;

// Si tu interfaz ICrud es genérica, descomenta "implements ICrud<Usuario>"
public class UsuarioDAO { 

    private MongoDatabase db;

    public UsuarioDAO() {
        // Obtenemos la conexión usando tu Singleton
        this.db = ConexionMongo.getInstancia().getDatabase();
    }

    // Método para guardar cualquier tipo de Usuario (Estudiante o Docente)
    public void crear(Usuario usuario) {
        Document doc = new Document();
        
        // 1. Datos comunes (del padre Usuario)
        doc.append("cedula", usuario.getCedula())
           .append("nombre", usuario.getNombre())
           .append("apellido", usuario.getApellido())
           .append("correo", usuario.getCorreo());

        // 2. Lógica para diferenciar dónde guardar
        if (usuario instanceof Estudiante) {
            // Es un ESTUDIANTE
            Estudiante est = (Estudiante) usuario;
            doc.append("carrera", est.getCarrera());
            
            // Guardamos en colección "estudiantes"
            MongoCollection<Document> collection = db.getCollection("estudiantes");
            collection.insertOne(doc);
            
        } else if (usuario instanceof Docente) {
            // Es un DOCENTE
            Docente docen = (Docente) usuario;
            doc.append("titulo", docen.getTitulo());
            doc.append("departamento", docen.getDepartamento());
            
            // Guardamos en colección "docentes"
            MongoCollection<Document> collection = db.getCollection("docentes");
            collection.insertOne(doc);
        }
    }
    public List<Usuario> leerTodos() {
        List<Usuario> listaUsuarios = new ArrayList<>();
        
        try {
            // 1. LEER ESTUDIANTES
            MongoCollection<Document> colEstudiantes = db.getCollection("estudiantes");
            for (Document doc : colEstudiantes.find()) {
                // Convertimos el Documento de Mongo a objeto Estudiante
                Estudiante est = new Estudiante(
                    doc.getString("cedula"),
                    doc.getString("nombre"),
                    doc.getString("apellido"),
                    doc.getString("correo"),
                    doc.getString("carrera")
                );
                listaUsuarios.add(est); // Lo agregamos a la lista general
            }

            // 2. LEER DOCENTES
            MongoCollection<Document> colDocentes = db.getCollection("docentes");
            for (Document doc : colDocentes.find()) {
                // Convertimos el Documento de Mongo a objeto Docente
                Docente docen = new Docente(
                    doc.getString("cedula"),
                    doc.getString("nombre"),
                    doc.getString("apellido"),
                    doc.getString("correo"),
                    doc.getString("titulo"),
                    doc.getString("departamento")
                );
                listaUsuarios.add(docen); // Lo agregamos a la lista general
            }
            
        } catch (Exception e) {
            System.out.println("Error al leer usuarios: " + e.getMessage());
        }

        return listaUsuarios;
    }
}
