package fr.radi3nt.noise;

import fr.radi3nt.noise.simplex.SimplexNoise;

import java.util.Random;

public class VaryingSeedNoise {

    public static float noise(Random random, float x, float y, float z, float size) {
        float xOffset = offsetBasedOnSeed(random);
        float yOffset = offsetBasedOnSeed(random);
        float zOffset = offsetBasedOnSeed(random);
        return SimplexNoise.noise(xOffset+x/size, yOffset+y/size, zOffset+z/size);
    }

    public static float noise(Random random, float x, float y, float size) {
        float xOffset = offsetBasedOnSeed(random);
        float yOffset = offsetBasedOnSeed(random);
        return SimplexNoise.noise(xOffset+x/size, yOffset+y/size);
    }

    private static float offsetBasedOnSeed(Random random) {
        return random.nextFloat();
    }

}
