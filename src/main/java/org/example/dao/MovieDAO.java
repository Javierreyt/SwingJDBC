package org.example.dao;

import org.example.model.Movie;
import org.example.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase DAO para la entidad Movie.
 */
public class MovieDAO implements DAO<Movie> {

    private final Connection connection;

    /**
     * Constructor que inicializa la conexión a la base de datos.
     *
     * @param conn Conexión a la base de datos.
     */
    public MovieDAO(Connection conn) {
        this.connection = conn;
    }

    /**
     * Encuentra todas las películas.
     *
     * @return Lista de todas las películas.
     */
    @Override
    public List<Movie> findAll() {
        return List.of();
    }

    /**
     * Encuentra las películas asociadas a un usuario.
     *
     * @param user Usuario cuyas películas se desean encontrar.
     * @return Lista de películas del usuario.
     */
    public ArrayList<Movie> findByUser(User user) {
        var output = new ArrayList<Movie>(0);

        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM Movie WHERE user_id = ?")) {
            var result = ps.executeQuery();
            while (result.next()) {
                Movie m = new Movie(
                        result.getInt(1),
                        result.getString(2),
                        result.getString(3),
                        result.getInt(4),
                        result.getString(5),
                        result.getString(6)
                );
                m.setUser(user);
                output.add(m);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return output;
    }

    /**
     * Encuentra una película por su ID.
     *
     * @param id ID de la película.
     * @return Película encontrada o null si no se encuentra.
     */
    @Override
    public Movie findById(Integer id) {
        Movie output = null;
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM Movie WHERE id=?")) {
            ps.setInt(1, id);
            ResultSet result = ps.executeQuery();
            if (result.next()) {
                output = new Movie(
                        result.getInt("id"),
                        result.getString("titulo"),
                        result.getString("genero"),
                        result.getInt("año"),
                        result.getString("descripcion"),
                        result.getString("director")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return output;
    }

    /**
     * Guarda una nueva película en la base de datos.
     *
     * @param movie Película a guardar.
     */
    @Override
    public void save(Movie movie) {
        try (PreparedStatement ps = connection.prepareStatement("INSERT INTO Movie(titulo, genero, año, descripcion, director) values(?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, movie.getTitle());
            ps.setString(2, movie.getGenre());
            ps.setInt(3, movie.getYear());
            ps.setString(4, movie.getDescription());
            ps.setString(5, movie.getDirector());
            int result = ps.executeUpdate();
            if (result > 0) {
                ResultSet keys = ps.getGeneratedKeys();
                if (keys.next()) {
                    Integer movie_id = keys.getInt(1);
                    movie.setId(movie_id);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Actualiza una película existente en la base de datos.
     *
     * @param movie Película a actualizar.
     */
    @Override
    public void update(Movie movie) {
        // Implementación pendiente
    }

    /**
     * Elimina una película de la base de datos.
     *
     * @param movie Película a eliminar.
     */
    @Override
    public void delete(Movie movie) {
        // Implementación pendiente
    }
}