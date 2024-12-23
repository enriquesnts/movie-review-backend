package uv.mx.movie_review_backend;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.server.ResponseStatusException;
import org.sqlite.SQLiteException;
import uv.mx.movie_review_backend.model.Movie;
import uv.mx.movie_review_backend.model.Review;
import uv.mx.movie_review_backend.model.Usuario;
import uv.mx.movie_review_backend.repo.MovieRepo;
import uv.mx.movie_review_backend.repo.ReviewRepo;
import uv.mx.movie_review_backend.repo.UsuarioRepo;

@CrossOrigin
@RestController
public class App {

    @Autowired
    MovieRepo movieRepo;

    @Autowired
    UsuarioRepo usuarioRepo;

    @Autowired
    ReviewRepo reviewRepo;

    @GetMapping("/peliculas")
    Iterable<Movie> listMovies() {
        return movieRepo.findAll();
    }

    @GetMapping("/peliculas/{id}")
    Optional<Movie> getMovie(@PathVariable int id) {
        return movieRepo.findById(id);
    }

    @PostMapping("/registro")
    ResponseEntity<Object> crearUsuario(@RequestBody Usuario nuevoUsuario) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(nuevoUsuario.setContrasena());
        nuevoUsuario.setContrasena(hashedPassword);
        try {
            Usuario saved = usuarioRepo.save(nuevoUsuario);
            System.out.println("Saved user: " + saved.getCorreo());
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            if (e.getCause().getCause() instanceof SQLiteException) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Correo ya existe");
            }
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/login")
    ResponseEntity<Object> login(@RequestBody Usuario usuario) {
        Optional<Usuario> existente = usuarioRepo.findByCorreo(usuario.getCorreo());
        if (existente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        // Compara la contraseña recibida con la que está guardada encriptida
        if (passwordEncoder.matches(usuario.getContrasena(), existente.get().getContrasena())) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

    }

    @PostMapping("/peliculas/{movieId}/review")
    ResponseEntity<Object> crearReview(@PathVariable Integer movieId, @RequestBody Review review) {
        if (!movieRepo.existsById(movieId)) {
            return ResponseEntity.notFound().build();
        }
        review.setMovieId(movieId);
        reviewRepo.save(review);
        return ResponseEntity.ok().body(review);
    }
}