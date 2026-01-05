package com.riwi.assesmentjava.infrastructure.adapters.out.notification;

import com.riwi.assesmentjava.application.ports.out.NotificationPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class NotificationAdapter implements NotificationPort {

    @Override
    public void notify(String message) {
        log.info("[NOTIFICATION] Broadcast: {}", message);
    }

    @Override
    public void notifyUser(String userId, String message) {
        log.info("[NOTIFICATION] To User {}: {}", userId, message);
    }
}
