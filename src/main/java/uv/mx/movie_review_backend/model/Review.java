package uv.mx.movie_review_backend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
@Table(name = "reviews")
@JsonIgnoreProperties(value = "usuario")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer review_id;
    private Integer user_id;
    private String review;
    private Integer rating;
    private Integer movie_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private Usuario usuario;

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Integer getUserId() {
        return user_id;
    }

    public void setUserId(Integer userId) {
        this.user_id = userId;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public Integer getMovieId() {
        return movie_id;
    }

    public void setMovieId(Integer movieId) {
        this.movie_id = movieId;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Integer getReview_id() {
        return review_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = Integer.parseInt(user_id);
    }

    @JsonProperty("nombreUsuario")
    public String getNombreUsuario() {
        return usuario.getNombre();
    }
}