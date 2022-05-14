package fr.radi3nt.maths.colour.colors;

import fr.radi3nt.maths.colour.colors.Colour;
import fr.radi3nt.maths.colour.colors.Colour;

public class ColorData {

    private final Colour colour;
    private final float alpha;

    public ColorData(Colour colour, float alpha) {
        this.colour = colour;
        this.alpha = alpha;
    }

    public ColorData(Colour colour) {
        this(colour, 1);
    }

    public Colour getColour() {
        return colour;
    }

    public float getAlpha() {
        return alpha;
    }
}
