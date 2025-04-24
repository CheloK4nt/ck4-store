package cl.ck4nt.backend.services;

import cl.ck4nt.backend.dtos.LoginRequest;
import cl.ck4nt.backend.entities.Usuario;
import cl.ck4nt.backend.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final UsuarioRepository usuarioRepository;

    @Autowired
    public AuthService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public String login(LoginRequest request) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByEmail(request.getEmail());

        if (usuarioOpt.isEmpty()) {
            return "❌ Usuario no encontrado";
        }

        Usuario usuario = usuarioOpt.get();

        if (!usuario.getPassword().equals(request.getPassword())) {
            return "❌ Contraseña incorrecta";
        }

        if (!usuario.getActivo()) {
            return "⛔ Usuario inactivo";
        }

        return "✅ Login exitoso. Bienvenido " + usuario.getNombre() + " [" + usuario.getRol() + "]";
    }
}
