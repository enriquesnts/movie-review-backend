package uv.mx.movie_review_backend.model;

import java.util.Collection;

import jakarta.persistence.*;

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
    @OneToMany
    @JoinColumn(name = "movie_id", nullable = false, insertable = false, updatable = false)
    private Collection<Review> reviews;

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

    public Collection<Review> getReviews() {
        return reviews;
    }

    public Integer getMovieId() {
        return movie_id;
    }
}