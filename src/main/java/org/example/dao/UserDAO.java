package org.example.dao;

import org.example.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Clase DAO para la entidad User.
 */
public class UserDAO implements DAO<User> {

    /**
     * Conexión a la base de datos.
     */
    private final Connection connection;

    /**
     * Constructor que inicializa la conexión a la base de datos.
     *
     * @param conn Conexión a la base de datos.
     */
    public UserDAO(Connection conn) {
        this.connection = conn;
    }

    /**
     * Valida un usuario con su nombre de usuario y contraseña.
     *
     * @param username Nombre de usuario.
     * @param password Contraseña.
     * @return Usuario validado o null si no se encuentra.
     */
    public User validateUser(String username, String password) {
        User output = null;
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM User WHERE nombre_usuario = ? AND contraseña = ?")) {
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                output = new User();
                output.setId(rs.getInt("id"));
                output.setUsername(rs.getString("nombre_usuario"));
                output.setPassword(rs.getString("contraseña"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return output;
    }

    /**
     * Encuentra todos los usuarios.
     *
     * @return Lista de todos los usuarios.
     */
    @Override
    public List<User> findAll() {
        return List.of();
    }

    /**
     * Encuentra un usuario por su ID.
     *
     * @param id ID del usuario.
     * @return Usuario encontrado o null si no se encuentra.
     */
    @Override
    public User findById(Integer id) {
        User output = null;
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM User WHERE id=?")) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                output = new User();
                output.setUsername(rs.getString("nombre_usuario"));
                output.setPassword(rs.getString("contraseña"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return output;
    }

    /**
     * Guarda un nuevo usuario en la base de datos.
     *
     * @param user Usuario a guardar.
     */
    @Override
    public void save(User user) {
        // Implementación pendiente
    }

    /**
     * Actualiza un usuario existente en la base de datos.
     *
     * @param user Usuario a actualizar.
     */
    @Override
    public void update(User user) {
        // Implementación pendiente
    }

    /**
     * Elimina un usuario de la base de datos.
     *
     * @param user Usuario a eliminar.
     */
    @Override
    public void delete(User user) {
        // Implementación pendiente
    }
}