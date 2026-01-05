package com.riwi.assesmentjava.application.usecases;

import com.riwi.assesmentjava.application.exception.BusinessRuleException;
import com.riwi.assesmentjava.application.ports.in.RegisterUserUseCase;
import com.riwi.assesmentjava.application.ports.out.UserRepositoryPort;
import com.riwi.assesmentjava.domain.model.User;

import java.util.UUID;

public class RegisterUserUseCaseImpl implements RegisterUserUseCase {

    private final UserRepositoryPort userRepositoryPort;

    public RegisterUserUseCaseImpl(UserRepositoryPort userRepositoryPort) {
        this.userRepositoryPort = userRepositoryPort;
    }

    @Override
    public User execute(User user) {
        if (userRepositoryPort.existsByUsername(user.getUsername())) {
            throw new BusinessRuleException("Username already exists");
        }
        if (userRepositoryPort.existsByEmail(user.getEmail())) {
            throw new BusinessRuleException("Email already exists");
        }

        user.setId(UUID.randomUUID());
        // Password encoding debe hacerse antes de llamar a este caso de uso o inyectar
        // un PasswordEncoderPort
        // Por pureza de arquitectura, el encoder es infraestructura.
        // Asumiremos que el password ya viene hasheado o lo hashearemos en el adaptador
        // (controller/service bad practice but pragmatic here)
        // Lo correcto: PasswordEncoderPort.

        return userRepositoryPort.save(user);
    }
}
