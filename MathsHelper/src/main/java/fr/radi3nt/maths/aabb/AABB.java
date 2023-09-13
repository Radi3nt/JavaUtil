package fr.radi3nt.maths.aabb;

public class AABB {

    private final AxisMapping xMapping;
    private final AxisMapping yMapping;
    private final AxisMapping zMapping;

    public AABB(AxisMapping xMapping, AxisMapping yMapping, AxisMapping zMapping) {
        this.xMapping = xMapping;
        this.yMapping = yMapping;
        this.zMapping = zMapping;
    }

    public AxisMapping getxMapping() {
        return xMapping;
    }

    public AxisMapping getyMapping() {
        return yMapping;
    }

    public AxisMapping getzMapping() {
        return zMapping;
    }
}
