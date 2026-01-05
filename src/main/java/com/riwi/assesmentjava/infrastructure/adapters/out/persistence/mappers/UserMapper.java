package com.riwi.assesmentjava.infrastructure.adapters.out.persistence.mappers;

import com.riwi.assesmentjava.domain.model.User;
import com.riwi.assesmentjava.infrastructure.adapters.out.persistence.entities.UserEntity;

public class UserMapper {

    public static UserEntity toEntity(User user) {
        if (user == null) {
            return null;
        }

        return UserEntity.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .password(user.getPassword())
                .build();
    }

    public static User toDomain(UserEntity entity) {
        if (entity == null) {
            return null;
        }

        return new User(
                entity.getId(),
                entity.getUsername(),
                entity.getEmail(),
                entity.getPassword());
    }
}
