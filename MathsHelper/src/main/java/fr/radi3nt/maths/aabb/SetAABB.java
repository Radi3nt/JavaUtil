package fr.radi3nt.maths.aabb;

public class SetAABB implements AABB {

    private final AxisMapping xMapping;
    private final AxisMapping yMapping;
    private final AxisMapping zMapping;

    public SetAABB(AxisMapping xMapping, AxisMapping yMapping, AxisMapping zMapping) {
        this.xMapping = xMapping;
        this.yMapping = yMapping;
        this.zMapping = zMapping;
    }

    public static SetAABB zero() {
        return new SetAABB(new AxisMapping(0, 0), new AxisMapping(0, 0), new AxisMapping(0, 0));
    }

    public void copy(AABB aabb) {
        copy(aabb.getxMapping(xMapping), aabb.getyMapping(yMapping), aabb.getzMapping(zMapping));
    }

    public void copy(AABB aabb, float added) {
        copy(aabb.getxMapping(xMapping), aabb.getyMapping(yMapping), aabb.getzMapping(zMapping), added);
    }


    private void copy(AxisMapping x, AxisMapping y, AxisMapping z) {
        copy(x.getMin(), x.getMax(), y.getMin(), y.getMax(), z.getMin(), z.getMax());
    }

    private void copy(AxisMapping x, AxisMapping y, AxisMapping z, float added) {
        copy(x.getMin()-added, x.getMax()+added, y.getMin()-added, y.getMax()+added, z.getMin()-added, z.getMax()+added);
    }

    public void extend(float added) {
        xMapping.extend(added);
        yMapping.extend(added);
        zMapping.extend(added);
    }

    public void copy(float minX, float maxX, float minY, float maxY, float minZ, float maxZ) {
        xMapping.set(minX, maxX);
        yMapping.set(minY, maxY);
        zMapping.set(minZ, maxZ);
    }

    public AxisMapping getxMapping() {
        return xMapping;
    }

    @Override
    public AxisMapping getxMapping(AxisMapping mapping) {
        return xMapping;
    }

    public AxisMapping getyMapping() {
        return yMapping;
    }

    @Override
    public AxisMapping getyMapping(AxisMapping mapping) {
        return yMapping;
    }

    public AxisMapping getzMapping() {
        return zMapping;
    }

    @Override
    public AxisMapping getzMapping(AxisMapping mapping) {
        return zMapping;
    }
}
