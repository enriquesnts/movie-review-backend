package uv.mx.movie_review_backend.repo;

import uv.mx.movie_review_backend.model.Usuario;

import org.springframework.data.repository.CrudRepository;

public interface UsuarioRepo extends CrudRepository<Usuario, Integer> {

}
