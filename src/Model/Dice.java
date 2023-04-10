package Model;

import java.util.concurrent.ThreadLocalRandom;

public class Dice implements DiceNumberGenerator {
    private static final int RANDOM_LOWER_INCLUSIVE = 1;
    private static final int RANDOM_UPPER_INCLUSIVE = 6;

    @Override
    public int generate() {
        return RANDOM_LOWER_INCLUSIVE + ThreadLocalRandom.current().nextInt(RANDOM_UPPER_INCLUSIVE - RANDOM_LOWER_INCLUSIVE + 1);
    }
}
