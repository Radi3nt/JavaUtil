package fr.radi3nt.noise.generator;

import fr.radi3nt.noise.FastSimplexNoise;

import java.util.Random;

public class FastSimplexNoise3D implements DoubleNoise3D {

    private final long seed;
    private final double size;

    private final double offsetX;
    private final double offsetY;
    private final double offsetZ;
    private final double offsetW;

    public FastSimplexNoise3D(long seed, double size) {
        this.seed = seed;
        this.size = size;

        Random random = new Random(seed);
        this.offsetX = random.nextDouble()*1024D-512D;
        this.offsetY = random.nextDouble()*1024D-512D;
        this.offsetZ = random.nextDouble()*1024D-512D;
        this.offsetW = random.nextDouble()*1024D-512D;
    }

    @Override
    public double noise(double x, double y, double z) {
        return FastSimplexNoise.noise(x*size + offsetX, y*size+ offsetY, z*size+ offsetZ)*0.5d+0.5d;
    }

    @Override
    public double noise(double x, double y, double z, double w) {
        return FastSimplexNoise.noise(x*size + offsetX, y*size+ offsetY, z*size+ offsetZ, w*size+offsetW)*0.5d+0.5d;
    }
}
