package fr.radi3nt.maths.aabb;

public class AxisMapping {

    private final double min;
    private final double max;

    public AxisMapping(double min, double max) {
        this.min = min;
        this.max = max;
    }

    public boolean intersect(AxisMapping axisMapping) {
        return (min <= axisMapping.max && max >= axisMapping.min);
    }
}
