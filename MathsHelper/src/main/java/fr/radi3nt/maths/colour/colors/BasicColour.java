package fr.radi3nt.maths.colour.colors;

import fr.radi3nt.maths.components.vectors.Vector3f;

import java.util.Objects;

public class BasicColour implements Colour {

    public static final BasicColour WHITE = new BasicColour(1f, 1f, 1f);
    public static final BasicColour BLACK = new BasicColour(0f, 0f, 0f);

    private final float red;
    private final float green;
    private final float blue;

    public BasicColour(Vector3f colour) {
        this(colour.getX(), colour.getY(), colour.getZ());
    }

    public BasicColour(float red, float green, float blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public BasicColour(float red, float green, float blue, float intensity) {
        this.red = red*intensity;
        this.green = green*intensity;
        this.blue = blue*intensity;
    }

    public BasicColour(float color) {
        this(color, color, color);
    }

    public BasicColour(int red, int green, int blue) {
        this.red = red/255f;
        this.green = green/255f;
        this.blue = blue/255f;
    }

    @Override
    public float getRed() {
        return red;
    }

    @Override
    public float getGreen() {
        return green;
    }

    @Override
    public float getBlue() {
        return blue;
    }

    @Override
    public String toString() {
        return "BasicColour{" +
                "red=" + red +
                ", green=" + green +
                ", blue=" + blue +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BasicColour)) return false;
        BasicColour that = (BasicColour) o;
        return Float.compare(that.getRed(), getRed()) == 0 && Float.compare(that.getGreen(), getGreen()) == 0 && Float.compare(that.getBlue(), getBlue()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRed(), getGreen(), getBlue());
    }

    @Override
    public Colour duplicate() {
        return new BasicColour(getRed(), getGreen(), getBlue());
    }
}
