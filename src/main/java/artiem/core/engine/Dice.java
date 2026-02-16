package artiem.core.engine;

import java.util.Random;

public class Dice {
    private static final Random RANDOM = new Random();

    public static int d20() {
        return RANDOM.nextInt(20) + 1;
    }
}
