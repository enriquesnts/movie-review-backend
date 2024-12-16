package uv.mx.movie_review_backend.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "users")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private String nombre;
    @Column(unique = true)
    private String correo;
    private String contrasena;

    public String getNombre() {
        return this.nombre;
    }

    public String getCorreo() {
        return this.correo;
    }

    public String setContrasena() {
        return this.contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY)
    private List<Review> reviews;

    public List<Review> getReviews() {
        return reviews;
    }

    public String getContrasena() {
        return contrasena;
    }
}