package de.neuefische.backend.generator;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@ConditionalOnProperty(prefix = "app", name = "id-generator", havingValue = "uuid", matchIfMissing = true)
public class IdFromUuidGenerator implements IdGeneratorInterface {
    @Override
    public String generateId() {
        return UUID.randomUUID().toString();
    }
}
