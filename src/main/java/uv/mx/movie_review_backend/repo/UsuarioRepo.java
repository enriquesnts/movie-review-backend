package uv.mx.movie_review_backend.repo;

import uv.mx.movie_review_backend.model.Usuario;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UsuarioRepo extends CrudRepository<Usuario, Integer> {
    Optional<Usuario> findByCorreo(String correo);
}
