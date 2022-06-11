package fr.radi3nt.noise.noise;

import fr.radi3nt.noise.Noise3D;
import fr.radi3nt.noise.SimplexNoise;

public class SimpleNoise3D implements Noise3D {

    private final float positionMultiplicator;
    private final float size;

    public SimpleNoise3D(float positionMultiplicator, float size) {
        this.positionMultiplicator = positionMultiplicator;
        this.size = size;
    }

    /*
    @Override
    public float getNoise2D(Vec2f position) {
        long actualSeed = (position.hashCode())*(seed);
        Random random = new Random(actualSeed);
        float rnd = random.nextFloat();
        return rnd;
    }

     */

    @Override
    public float getNoise2D(long x, long y) {
        return (float) (SimplexNoise.noise(x* positionMultiplicator, y* positionMultiplicator)+1)/2f*size;
    }

    @Override
    public float getNoise3D(long x, long y, long z) {

        float ab = getNoise2D(x, y);
        float bc = getNoise2D(y, z);
        float ac = getNoise2D(x, z);

        float ba = getNoise2D(y, x);
        float cb = getNoise2D(z, y);
        float ca = getNoise2D(z, x);

        float abc = ab + bc + ac + ba + cb + ca;

        return abc/6f;
    }
}
