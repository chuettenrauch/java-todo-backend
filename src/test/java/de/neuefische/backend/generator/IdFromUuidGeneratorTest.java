package de.neuefische.backend.generator;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class IdFromUuidGeneratorTest {

    @Test
    void generateId() {
        int numOfIdGenerations = 50;
        IdGeneratorInterface sut = new IdFromUuidGenerator();

        Set<String> generatedIds = new HashSet<>();
        for (int i = 0; i < numOfIdGenerations; i++) {
            generatedIds.add(sut.generateId());
        }

        assertEquals(numOfIdGenerations, generatedIds.size());
    }
}