package fr.radi3nt.noise;

public class TerrainHeight2D implements Noise3D {

    private final float[] amplitudes;
    private final float power;
    private final float size;
    private final float height;
    private final float smoothing;

    public TerrainHeight2D(float[] amplitudes, float power, float size, float height, float smoothing) {
        this.amplitudes = amplitudes;
        this.power = power;
        this.size = size;
        this.height = height;
        this.smoothing = smoothing;
    }

    @Override
    public float getNoise2D(long x, long y) {
        double nx= x/size;
        double ny= y/size;
        double height = 0;
        double sumAmplitude = 0;
        for (int i = 0; i < amplitudes.length; i++) {
            double amplitude = amplitudes[i];
            sumAmplitude+=amplitude;
            double pow = Math.pow(2, i);
            height+= amplitude * SimplexNoise.noise(pow* nx, pow*ny);
        }
        height = height/sumAmplitude;
        height = Math.pow(height, power);
        return (float) (height*this.height);
    }

    @Override
    public float getNoise3D(long x, long y, long z) {
        float currentHeight = getNoise2D(x, z);
        float smoothPart = (1-(y-currentHeight)/smoothing);
        return y<currentHeight ? 1 : smoothPart > 0 ? Math.max(smoothPart, 0) : 0;
    }

}
