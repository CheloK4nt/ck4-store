package cl.ck4nt.backend.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;

@Component
public class JwtUtil {

    // 🔐 Clave secreta (debería estar en application.properties en producción)
    private final String SECRET_KEY = "ck4-super-secret-key-jwt-2025-32chars";

    // ⏳ Tiempo de expiración del token (en milisegundos)
    private final long EXPIRATION_TIME = 1000 * 60 * 60 * 10; // 10 horas

    // ✅ Generar token
    public String generateToken(String email, String role) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", role); // puedes agregar más claims si necesitas

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8)), SignatureAlgorithm.HS256)
                .compact();
    }

    // 🔍 Obtener email desde el token
    public String extractEmail(String token) {
        return extractAllClaims(token).getSubject();
    }

    // ✅ Validar token
    public boolean isTokenValid(String token, String email) {
        String extractedEmail = extractEmail(token);
        return (email.equals(extractedEmail) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractAllClaims(token).getExpiration().before(new Date());
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }
}
