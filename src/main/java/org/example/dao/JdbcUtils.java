package org.example.dao;

import lombok.Data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 * Clase que proporciona métodos para obtener una conexión JDBC.
 */
@Data
public class JdbcUtils {
    /**
     * Bloque estático que inicializa la conexión JDBC.
     */
    private static Connection connection;

    static {
        String url = "jdbc:mysql://localhost:3306/AD";
        String user = "root";
        String password = System.getenv("MYSQL_ROOT_PASSWORD");
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Devuelve la conexión JDBC.
     *
     * @return la conexión JDBC
     */
    public static Connection getConnection() {
        return connection;
    }
}
