package org.example.model;

import lombok.Data;

import java.io.Serializable;

/**
 * Objeto de Transferencia de Datos para CopiaMovie.
 */
@Data
public class CopiaMovieDTO implements Serializable {
    private Movie movie;
    private Copia copia;

    /**
     * Construye un nuevo CopiaMovieDTO con la película y copia especificadas.
     *
     * @param movie la película
     * @param copia la copia
     */
    public CopiaMovieDTO(Movie movie, Copia copia) {
        this.movie = movie;
        this.copia = copia;
    }
}
