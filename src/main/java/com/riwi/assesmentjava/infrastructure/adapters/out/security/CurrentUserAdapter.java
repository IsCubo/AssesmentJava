package com.riwi.assesmentjava.infrastructure.adapters.out.security;

import com.riwi.assesmentjava.application.ports.out.CurrentUserPort;
import com.riwi.assesmentjava.infrastructure.adapters.out.persistence.entities.UserEntity;
import com.riwi.assesmentjava.infrastructure.adapters.out.persistence.repositories.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CurrentUserAdapter implements CurrentUserPort {

    private final UserJpaRepository userJpaRepository;

    @Override
    public UUID getCurrentUserId() {
        String username = getCurrentUsername();

        return userJpaRepository.findByUsername(username)
                .map(UserEntity::getId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
    }

    @Override
    public String getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()
                || "anonymousUser".equals(authentication.getPrincipal())) {
            throw new RuntimeException("No user authenticated");
        }
        return authentication.getName();
    }
}
