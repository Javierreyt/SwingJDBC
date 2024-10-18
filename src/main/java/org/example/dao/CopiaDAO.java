package org.example.dao;

import org.example.model.Copia;
import org.example.model.CopiaMovieDTO;
import org.example.model.Movie;
import org.example.model.Session;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase DAO para la entidad Copia.
 */
public class CopiaDAO implements DAO<Copia> {
    private final Connection connection;

    /**
     * Constructor de CopiaDAO.
     *
     * @param conn la conexión a la base de datos
     */
    public CopiaDAO(Connection conn) {
        this.connection = conn;
    }

    /**
     * Encuentra todas las copias.
     *
     * @return una lista de todas las copias
     */
    @Override
    public List<Copia> findAll() {
        return List.of();
    }

    /**
     * Encuentra una copia por su ID.
     *
     * @param id el ID de la copia
     * @return la copia encontrada o null si no se encuentra
     */
    @Override
    public Copia findById(Integer id) {
        return null;
    }

    /**
     * Guarda una nueva copia.
     *
     * @param copia la copia a guardar
     */
    @Override
    public void save(Copia copia) {

    }

    /**
     * Actualiza una copia existente.
     *
     * @param copia la copia a actualizar
     */
    @Override
    public void update(Copia copia) {

    }

    /**
     * Elimina una copia.
     *
     * @param copia la copia a eliminar
     */
    @Override
    public void delete(Copia copia) {

    }

    /**
     * Encuentra copias por el ID de la película.
     *
     * @param movieId el ID de la película
     * @return una lista de copias de la película
     */
    public List<Copia> findByMovie(Integer movieId) {
        List<Copia> copias = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM Copia WHERE id_pelicula = ?")) {
            ps.setInt(1, movieId);
            ResultSet result = ps.executeQuery();
            while (result.next()) {
                copias.add(new Copia(
                        result.getInt("id"),
                        result.getInt("id_pelicula"),
                        result.getInt("id_usuario"),
                        result.getString("estado"),
                        result.getString("soporte")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return copias;
    }

    /**
     * Obtiene copias por el ID del usuario.
     *
     * @param userId el ID del usuario
     * @return una lista de copias del usuario
     */
    public List<Copia> getCopiasByUser(int userId) {
        List<Copia> copias = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Copia WHERE id_usuario = ?")) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                copias.add(new Copia(
                        rs.getInt("id"),
                        rs.getInt("id_pelicula"),
                        rs.getInt("id_usuario"),
                        rs.getString("estado"),
                        rs.getString("soporte")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return copias;
    }

    /**
     * Realiza una unión de tablas por el ID del usuario.
     *
     * @param id el ID del usuario
     * @return una lista de CopiaMovieDTO
     */
    public ArrayList<CopiaMovieDTO> joinTableById(int id) {
        var output = new ArrayList<CopiaMovieDTO>(0);
        try (PreparedStatement ps = connection.prepareStatement("SELECT Movie.id, Movie.titulo, Movie.genero, Movie.año, " +
                "Movie.descripcion, Movie.director, Copia.estado, Copia.soporte " +
                "FROM Movie JOIN Copia ON Movie.id = Copia.id_pelicula WHERE Copia.id_usuario = ?")) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Movie movie = new Movie(
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getString("genero"),
                        rs.getInt("año"),
                        rs.getString("descripcion"),
                        rs.getString("director"),
                        Session.user
                );

                Copia copia = new Copia(
                        rs.getString("estado"),
                        rs.getString("soporte")
                );
                output.add(new CopiaMovieDTO(movie, copia));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return output;
    }
}