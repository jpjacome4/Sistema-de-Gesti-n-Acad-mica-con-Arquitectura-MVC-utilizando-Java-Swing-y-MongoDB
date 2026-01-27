/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.espe.dao;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class ConexionMongo {

    // 2. ATRIBUTOS
    private static ConexionMongo instancia;
    private MongoClient mongoClient;
    private MongoDatabase database;

    // 3. CONSTRUCTOR PRIVADO
    private ConexionMongo() {
        try {
            // Conectar a localhost en el puerto 27017
            mongoClient = MongoClients.create("mongodb://localhost:27017");
            
            // Seleccionar la base de datos "SGA"
            database = mongoClient.getDatabase("SGA");
            
        } catch (Exception e) {
            // Usamos 'Exception' genérico para evitar errores de validación
            System.err.println("Error de conexión: " + e.getMessage());
        }
    }

    // 4. METODO SINGLETON (Para obtener la instancia)
    public static ConexionMongo getInstancia() {
        if (instancia == null) {
            instancia = new ConexionMongo();
        }
        return instancia;
    }

    // 5. METODO PARA OBTENER LA BASE DE DATOS
    public MongoDatabase getDatabase() {
        return database;
    }
}
