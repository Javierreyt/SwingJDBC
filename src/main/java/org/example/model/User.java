package org.example.model;

import lombok.Data;

import java.io.Serializable;

/**
 * Representa un usuario.
 */
@Data
public class User implements Serializable {
    private int id;
    private String username;
    private String password;
}