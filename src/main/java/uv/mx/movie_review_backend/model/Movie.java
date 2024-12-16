package uv.mx.movie_review_backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer movie_id;
    private String title;
    private int year;
    private String external_id;
    private String movie_poster_url;

    public String getMovie_poster_url() {
        return movie_poster_url;
    }

    public String getExternal_id() {
        return external_id;
    }

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }
}