package uv.mx.movie_review_backend.auth;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import uv.mx.movie_review_backend.config.Utils;

import java.io.IOException;

@Component
public class JWTAuthorizationFilter extends OncePerRequestFilter {

    private final String HEADER = "Authorization";
    private final String PREFIX = "Bearer ";
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        try {
            if (!existeJWTToken(request, response)) {
                returnAuthError(response);
                return;
            }
            validarToken(request);
            chain.doFilter(request, response);
        } catch (Exception e) {
            if (e instanceof JwtException) {
                returnAuthError(response);
            }
            throw e;
        }
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        // Indicamos que no se valide el token para el login y el registro
        // o si el request es options
        return request.getRequestURI().contains("login")
                || request.getRequestURI().contains("registro")
                || request.getMethod().equalsIgnoreCase("OPTIONS");

    }
    private void returnAuthError(HttpServletResponse response) throws IOException {
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.sendError(HttpServletResponse.SC_FORBIDDEN, "Token no autorizado");
    }

    private void validarToken(HttpServletRequest request) {
        String jwtToken = request.getHeader(HEADER).replace(PREFIX, "");
        Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(Decoders.BASE64.decode(Utils.Config.JWT_SECRET)))
                .build().parseSignedClaims(jwtToken);
    }

    private boolean existeJWTToken(HttpServletRequest request, HttpServletResponse res) {
        String authenticationHeader = request.getHeader(HEADER);
        return authenticationHeader != null && authenticationHeader.startsWith(PREFIX);
    }
}
