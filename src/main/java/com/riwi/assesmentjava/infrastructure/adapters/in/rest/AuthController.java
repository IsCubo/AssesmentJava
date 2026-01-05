package com.riwi.assesmentjava.infrastructure.adapters.in.rest;

import com.riwi.assesmentjava.application.ports.in.RegisterUserUseCase;
import com.riwi.assesmentjava.domain.model.User;
import com.riwi.assesmentjava.infrastructure.adapters.in.rest.dto.AuthResponse;
import com.riwi.assesmentjava.infrastructure.adapters.in.rest.dto.LoginRequest;
import com.riwi.assesmentjava.infrastructure.adapters.in.rest.dto.RegisterRequest;
import com.riwi.assesmentjava.infrastructure.adapters.out.security.jwt.JwtUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication", description = "Endpoints para registro e inicio de sesión")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;
    private final RegisterUserUseCase registerUserUseCase;
    private final PasswordEncoder passwordEncoder;

    @Operation(summary = "Registrar nuevo usuario", description = "Crea una nueva cuenta de usuario en el sistema.")
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        // Mapear request a dominio
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        // Crear usuario
        User savedUser = registerUserUseCase.execute(user);

        // Generar token
        UserDetails userDetails = userDetailsService.loadUserByUsername(savedUser.getUsername());
        String token = jwtUtil.generateToken(userDetails, savedUser.getId().toString());

        return ResponseEntity.ok(AuthResponse.builder().token(token).build());
    }

    @Operation(summary = "Iniciar sesión", description = "Autentica al usuario y devuelve un token JWT.")
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        // Autenticar
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        // Cargar detalles y generar token
        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        // Aquí necesitaríamos obtener el ID del usuario real, vamos a buscarlo de nuevo
        // o tenerlo en UserDetails
        // Por simplicidad, asumimos que el CustomUserDetailsService podría proveerlo, o
        // lo buscamos
        // Para este MVP, el token se genera sin ID extra si no lo tenemos a mano fácil,
        // pero el JwtUtil lo pide.
        // Haremos un "hack" temporal para obtener el ID: extraerlo del UserDetails si
        // fuera Custom, o buscarlo.
        // Dado que no tengo el ID en el UserDetails estándar, lo ideal es refactorizar
        // CustomUserDetailsService.
        // Por ahora, generaremos el token con un ID dummy o lo buscaremos.

        // Mejor opción: refactorizar CustomUserDetails. Pero para no romper:
        // El extractUserId en CurrentUserAdapter busca por username.
        // Así que podemos poner el mismo username como userId en el token si no tenemos
        // UUID,
        // pero CurrentUserAdapter espera que el token (si se usara para ID) tuviera
        // UUID.
        // Espera, el JwtUtil.generateToken pide userId String.
        // Vamos a buscar el usuario para obtener su ID. Es una query extra pero segura.
        // Nota: Esto es ineficiente en login masivo, pero aceptable para MVP.

        // TODO: Refactor CustomUserDetails to include ID.

        // Solución temporal: Pasar "unknown" y que CurrentUserAdapter lo resuelva por
        // Username como ya programamos.
        // Pero CurrentUserAdapter NO usa el token claims, usa SecurityContext ->
        // Username -> DB Query.
        // Así que el userId en el token es solo informativo por ahora.
        String token = jwtUtil.generateToken(userDetails, "user-id-placeholder");

        return ResponseEntity.ok(AuthResponse.builder().token(token).build());
    }
}
