package fr.radi3nt.noise.generators;

public abstract class OctaveGenerator {
    protected final NoiseGenerator[] octaves;
    protected double xScale = 1.0D;
    protected double yScale = 1.0D;
    protected double zScale = 1.0D;

    protected OctaveGenerator(NoiseGenerator[] octaves) {
        this.octaves = octaves;
    }

    public void setScale(double scale) {
        this.setXScale(scale);
        this.setYScale(scale);
        this.setZScale(scale);
    }

    public double getXScale() {
        return this.xScale;
    }

    public void setXScale(double scale) {
        this.xScale = scale;
    }

    public double getYScale() {
        return this.yScale;
    }

    public void setYScale(double scale) {
        this.yScale = scale;
    }

    public double getZScale() {
        return this.zScale;
    }

    public void setZScale(double scale) {
        this.zScale = scale;
    }

    public NoiseGenerator[] getOctaves() {
        return this.octaves.clone();
    }

    public double noise(double x, double frequency, double amplitude) {
        return this.noise(x, 0.0D, 0.0D, frequency, amplitude);
    }

    public double noise(double x, double frequency, double amplitude, boolean normalized) {
        return this.noise(x, 0.0D, 0.0D, frequency, amplitude, normalized);
    }

    public double noise(double x, double y, double frequency, double amplitude) {
        return this.noise(x, y, 0.0D, frequency, amplitude);
    }

    public double noise(double x, double y, double frequency, double amplitude, boolean normalized) {
        return this.noise(x, y, 0.0D, frequency, amplitude, normalized);
    }

    public double noise(double x, double y, double z, double frequency, double amplitude) {
        return this.noise(x, y, z, frequency, amplitude, false);
    }

    public double noise(double x, double y, double z, double frequency, double amplitude, boolean normalized) {
        double result = 0.0D;
        double amp = 1.0D;
        double freq = 1.0D;
        double max = 0.0D;
        x *= this.xScale;
        y *= this.yScale;
        z *= this.zScale;
        NoiseGenerator[] var23;
        int var22 = (var23 = this.octaves).length;

        for(int var21 = 0; var21 < var22; ++var21) {
            NoiseGenerator octave = var23[var21];
            result += octave.noise(x * freq, y * freq, z * freq) * amp;
            max += amp;
            freq *= frequency;
            amp *= amplitude;
        }

        if (normalized) {
            result /= max;
        }

        return result;
    }
}
