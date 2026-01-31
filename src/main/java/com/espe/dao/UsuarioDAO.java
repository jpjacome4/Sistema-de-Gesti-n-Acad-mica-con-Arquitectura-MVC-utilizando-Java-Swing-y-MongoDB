package com.espe.dao;

import com.espe.modelo.Docente;
import com.espe.modelo.Estudiante;
import com.espe.modelo.Usuario;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
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
}
