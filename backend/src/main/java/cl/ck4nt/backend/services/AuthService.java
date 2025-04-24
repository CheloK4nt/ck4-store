package cl.ck4nt.backend.services;

import cl.ck4nt.backend.dtos.LoginRequest;
import cl.ck4nt.backend.entities.Usuario;
import cl.ck4nt.backend.repositories.UsuarioRepository;
import cl.ck4nt.backend.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@Service
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Autowired
    public AuthService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public String login(LoginRequest request) {
        System.out.println("🔐 Intentando login con: " + request.getEmail());

        Optional<Usuario> userOpt = usuarioRepository.findByEmail(request.getEmail());

        if (userOpt.isEmpty()) {
            System.out.println("❌ Usuario no encontrado");
            throw new RuntimeException("Usuario no encontrado");
        }

        Usuario user = userOpt.get();
        System.out.println("👤 Usuario encontrado: " + user.getEmail());

        boolean passwordMatches = passwordEncoder.matches(request.getPassword(), user.getPassword());
        System.out.println("🔍 Comparando password: " + request.getPassword() + " vs hash: " + user.getPassword());
        System.out.println("✅ ¿Password válida? " + passwordMatches);

        if (!passwordMatches) {
            throw new RuntimeException("Credenciales inválidas");
        }

        String token = jwtUtil.generateToken(user.getEmail(), user.getRol());
        System.out.println("🔑 Token generado: " + token);
        return token;
    }
}
