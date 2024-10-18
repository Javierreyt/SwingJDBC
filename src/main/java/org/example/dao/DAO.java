package org.example.dao;

import java.util.List;

/**
 * Interfaz genérica para operaciones CRUD.
 *
 * @param <T> el tipo de objeto con el que trabaja esta DAO
 */
public interface DAO<T> {
    /**
     * Encuentra y devuelve una lista de todos los objetos.
     *
     * @return una lista de todos los objetos
     */
    public List<T> findAll();

    /**
     * Encuentra y devuelve un objeto por su ID.
     *
     * @param id el ID del objeto a encontrar
     * @return el objeto encontrado, o null si no se encuentra
     */
    public T findById(Integer id);

    /**
     * Guarda un nuevo objeto.
     *
     * @param t el objeto a guardar
     */
    public void save(T t);

    /**
     * Actualiza un objeto existente.
     *
     * @param t el objeto a actualizar
     */
    public void update(T t);

    /**
     * Elimina un objeto.
     *
     * @param t el objeto a eliminar
     */
    public void delete(T t);
}