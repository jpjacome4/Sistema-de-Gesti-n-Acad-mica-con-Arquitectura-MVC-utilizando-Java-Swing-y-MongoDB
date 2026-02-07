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
    
    
    public boolean eliminar(String cedula, String tipo) {
        try {
            // Determinamos la colección según el tipo de usuario
            String nombreColeccion = tipo.equalsIgnoreCase("docente") ? "docentes" : "estudiantes";
            com.mongodb.client.MongoCollection<org.bson.Document> coleccion = db.getCollection(nombreColeccion);
            
            // Ejecutamos la eliminación por cédula
            coleccion.deleteOne(new org.bson.Document("cedula", cedula));
            return true;
        } catch (Exception e) {
            System.err.println("Error al eliminar: " + e.getMessage());
            return false;
        }
    }
    
    // Método para actualizar datos de Docentes o Estudiantes
    public boolean actualizar(Usuario usuario) {
        try {
            // 1. Filtro: Buscamos por la cédula (que no cambia)
            org.bson.Document filtro = new org.bson.Document("cedula", usuario.getCedula());

            // 2. Datos generales (comunes para ambos)
            org.bson.Document datosBasicos = new org.bson.Document("nombre", usuario.getNombre())
                    .append("apellido", usuario.getApellido())
                    .append("correo", usuario.getCorreo());
            
            // 3. Si es DOCENTE, actualizamos su colección y campos extras
            if (usuario instanceof Docente) {
               Docente d = (Docente) usuario;
                datosBasicos.append("titulo", d.getTitulo())
                        .append("departamento", d.getDepartamento());
            
                db.getCollection("docentes").updateOne(filtro, new org.bson.Document("$set", datosBasicos));
                return true;
            } 
        
            // 4. Si es ESTUDIANTE, actualizamos su colección y su carrera
            else if (usuario instanceof Estudiante) {
                Estudiante e = (Estudiante) usuario;
                datosBasicos.append("carrera", e.getCarrera());
            
                db.getCollection("estudiantes").updateOne(filtro, new org.bson.Document("$set", datosBasicos));
                return true;
            }

        } catch (Exception e) {
            System.err.println("Error al actualizar en DAO: " + e.getMessage());
        }
        return false;
    }
}
