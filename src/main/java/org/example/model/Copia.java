package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * Representa una copia de una película.
 */
@Data
@AllArgsConstructor
public class Copia implements Serializable {
    private Integer id;
    private Integer id_usuario;
    private Integer id_pelicula;
    private String estado;
    private String soporte;

    /**
     * Constructor que inicializa una copia con estado y soporte.
     *
     * @param estado el estado de la copia
     * @param soporte el soporte de la copia
     */
    public Copia(String estado, String soporte) {
        this.estado = estado;
        this.soporte = soporte;
    }
}