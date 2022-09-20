package fr.radi3nt.noise;

import java.util.Random;

public class SeededFastSimplexNoise {

    private final double xOffset;
    private final double yOffset;
    private final double zOffset;

    public SeededFastSimplexNoise(long seed) {
        Random random = new Random(seed);
        xOffset = random.nextDouble();
        yOffset = random.nextDouble();
        zOffset = random.nextDouble();
    }

    public double noise(double x, double y, double z) {
        return FastSimplexNoise.noise(x + xOffset, y + yOffset, z + zOffset);
    }

    public double noise(double x, double z) {
        return FastSimplexNoise.noise(x + xOffset, z + zOffset);
    }
}
