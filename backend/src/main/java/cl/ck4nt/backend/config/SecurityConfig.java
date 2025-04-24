package cl.ck4nt.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // deshabilita CSRF (opcional para APIs REST)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                            "/api/auth/login",
                            "/"
                        ).permitAll() // permite el login sin auth
                        .anyRequest().authenticated() // el resto requiere autenticación
                );
        return http.build();
    }
}
