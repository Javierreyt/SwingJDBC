package org.example.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
/**
 * Representa una película.
 */
@Data
@AllArgsConstructor
public class Movie implements Serializable {
    private Integer id;
    private String title;
    private String genre;
    private Integer year;
    private String description;
    private String director;
    private User user;

    /**
     * Constructor que inicializa una película con los detalles especificados.
     *
     * @param id el identificador de la película
     * @param title el título de la película
     * @param genre el género de la película
     * @param year el año de lanzamiento de la película
     * @param description la descripción de la película
     * @param director el director de la película
     */
    public Movie(Integer id, String title, String genre, Integer year, String description, String director) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.year = year;
        this.description = description;
        this.director = director;
    }

    /**
     * Metodo sobreescrito del toString para mostrar los datos de una película
     */
    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", platform='" + genre + '\'' +
                ", year=" + year +
                ", description='" + description + '\'' +
                ", director=" + director +
                '}';
    }
}