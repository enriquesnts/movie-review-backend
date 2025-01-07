package uv.mx.movie_review_backend.auth;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import uv.mx.movie_review_backend.config.Utils;
import uv.mx.movie_review_backend.model.Usuario;

import java.util.Date;

public class JwtHelper {
    public static String createJwtToken(Usuario usuario) {
        try {
            String correo = usuario.getCorreo();
            JwtBuilder builder = Jwts.builder();
            builder.subject(correo);
            builder.issuer("movie_review");
            builder.issuedAt(new Date());
            var key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(Utils.Config.JWT_SECRET));
            builder.signWith(key);
            return builder.compact();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
