package de.neuefische.backend.generator;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class IdFromUuidGenerator implements IdGeneratorInterface {
    @Override
    public String generateId() {
        return UUID.randomUUID().toString();
    }
}
