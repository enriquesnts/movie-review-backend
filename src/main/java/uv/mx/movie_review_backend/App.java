
package uv.mx.movie_review_backend;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.server.ResponseStatusException;
import uv.mx.movie_review_backend.model.Movie;
import uv.mx.movie_review_backend.model.Usuario;
import uv.mx.movie_review_backend.repo.MovieRepo;
import uv.mx.movie_review_backend.repo.UsuarioRepo;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
public class App {

    @Autowired
    MovieRepo movieRepo;

    @Autowired
    UsuarioRepo usuarioRepo;

    @GetMapping("/peliculas")
    Iterable<Movie> listMovies() {
        return movieRepo.findAll();
    }

    @GetMapping("/peliculas/{id}")
    Optional<Movie> getMovie(@PathVariable int id) {
        return movieRepo.findById(id);
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/registro")
    ResponseEntity<Object> crearUsuario(@RequestBody Usuario nuevoUsuario) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(nuevoUsuario.contrasena());
        nuevoUsuario.contrasena(hashedPassword);
        try {
            Usuario saved = usuarioRepo.save(nuevoUsuario);
            System.out.println("Saved user: " + saved.getCorreo());
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            if (e.getMessage().contains("correo")) {
               throw new ResponseStatusException(HttpStatus.CONFLICT, "Correo ya existe");
            }
            return ResponseEntity.internalServerError().build();
        }

    }
}