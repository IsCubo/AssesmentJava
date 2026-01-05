package com.riwi.assesmentjava.application.ports.in;

import com.riwi.assesmentjava.domain.model.User;

public interface RegisterUserUseCase {
    User execute(User user);
}
