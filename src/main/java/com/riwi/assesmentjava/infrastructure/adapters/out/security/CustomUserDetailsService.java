package com.riwi.assesmentjava.infrastructure.adapters.out.security;

import com.riwi.assesmentjava.infrastructure.adapters.out.persistence.entities.UserEntity;
import com.riwi.assesmentjava.infrastructure.adapters.out.persistence.repositories.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserJpaRepository userJpaRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userJpaRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        // Retornamos un User de Spring Security
        // En una implementación más compleja aquí cargaríamos roles/permisos
        return new User(
                userEntity.getUsername(),
                userEntity.getPassword(),
                Collections.emptyList() // No roles for now
        );
    }
}
