package fr.radi3nt.noise.simplex;

import java.util.Random;

public class SeededSimplexNoise {

    private final long seed;

    private final float offsetX;
    private final float offsetY;
    private final float offsetZ;

    public SeededSimplexNoise(long seed) {
        this.seed = seed;
        Random random = new Random(seed);
        offsetX = (float) (random.nextDouble()*1024);
        offsetY = (float) (random.nextDouble()*1024);
        offsetZ = (float) (random.nextDouble()*1024);
    }

    public float noise(final float x, final float y) {
        return SimplexNoise.noise(x+offsetX, y+offsetY);
    }

    public float noise(final float x, final float y, final float z) {
        return SimplexNoise.noise(x+offsetX, y+offsetY, z+offsetZ);
    }


}
