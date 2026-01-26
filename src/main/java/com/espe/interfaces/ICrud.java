package com.espe.interfaces;
import java.util.List;

// El <T> se pone AQUÍ, en la definición, no en el nombre del archivo
public interface ICrud<T> {
    
    // Método para guardar un objeto nuevo
    boolean crear(T objeto);
    
    // Método para traer todos los registros en una lista
    List<T> leerTodos();
    
    // Método para modificar un registro existente
    boolean actualizar(T objeto);
    
    // Método para borrar usando el ID (cédula o código)
    boolean eliminar(String id);
    
    // Método para buscar uno solo
    T buscarPorId(String id);
}