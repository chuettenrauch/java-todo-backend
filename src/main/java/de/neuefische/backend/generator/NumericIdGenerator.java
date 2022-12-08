package de.neuefische.backend.generator;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
@ConditionalOnProperty(prefix = "app", name = "id-generator", havingValue = "numeric")
public class NumericIdGenerator implements IdGeneratorInterface {
    @Override
    public String generateId() {
        int lowerBound = 1;
        int upperBound = Integer.MAX_VALUE;
        Random random = new Random();

        int randomNumber = random.nextInt(lowerBound, upperBound);

        return String.valueOf(randomNumber);
    }
}
