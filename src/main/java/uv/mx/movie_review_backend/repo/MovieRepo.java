package uv.mx.movie_review_backend.repo;

import org.springframework.data.repository.CrudRepository;

import uv.mx.movie_review_backend.model.Movie;

public interface MovieRepo extends CrudRepository<Movie, Integer> {

}
