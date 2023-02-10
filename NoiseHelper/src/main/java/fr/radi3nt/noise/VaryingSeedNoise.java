package fr.radi3nt.noise;

import fr.radi3nt.noise.simplex.SimplexNoise;

import java.util.Random;

public class VaryingSeedNoise {

    public static float noise(int seed, float x, float y, float z, float size) {
        Random random = new Random(seed);
        float xOffset = random.nextFloat()+random.nextInt();
        float yOffset = random.nextFloat()+random.nextInt();
        float zOffset = random.nextFloat()+random.nextInt();
        return SimplexNoise.noise(xOffset+x/size, yOffset+y/size, zOffset+z/size);
    }

    public static float noise(int seed, float x, float y, float size) {
        Random random = new Random(seed);
        float xOffset = random.nextFloat()+random.nextInt();
        float yOffset = random.nextFloat()+random.nextInt();
        return SimplexNoise.noise(xOffset+x/size, yOffset+y/size);
    }

}
