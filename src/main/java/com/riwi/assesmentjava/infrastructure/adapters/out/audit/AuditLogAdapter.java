package com.riwi.assesmentjava.infrastructure.adapters.out.audit;

import com.riwi.assesmentjava.application.ports.out.AuditLogPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
public class AuditLogAdapter implements AuditLogPort {

    @Override
    public void register(String action, UUID entityId) {
        log.info("[AUDIT] Action: {}, EntityID: {}", action, entityId);
    }

    @Override
    public void register(String action, UUID entityId, UUID userId) {
        log.info("[AUDIT] Action: {}, EntityID: {}, UserID: {}", action, entityId, userId);
    }
}
