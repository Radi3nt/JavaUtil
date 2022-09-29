package fr.radi3nt.maths.components;

public class Vector3D implements Cloneable {

    protected double x;
    protected double y;
    protected double z;

    public Vector3D() {
        this.x = 0.0D;
        this.y = 0.0D;
        this.z = 0.0D;
    }

    public Vector3D(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3D(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3D(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3D add(Vector3D vec) {
        this.x += vec.x;
        this.y += vec.y;
        this.z += vec.z;
        return this;
    }

    public Vector3D add(double offsetX, double offsetY, double offsetZ) {
        this.x += offsetX;
        this.y += offsetY;
        this.z += offsetZ;
        return this;
    }

    public Vector3D subtract(Vector3D vec) {
        this.x -= vec.x;
        this.y -= vec.y;
        this.z -= vec.z;
        return this;
    }

    public Vector3D multiply(Vector3D vec) {
        this.x *= vec.x;
        this.y *= vec.y;
        this.z *= vec.z;
        return this;
    }

    public Vector3D divide(Vector3D vec) {
        this.x /= vec.x;
        this.y /= vec.y;
        this.z /= vec.z;
        return this;
    }

    public Vector3D divide(double div) {
        this.x /= div;
        this.y /= div;
        this.z /= div;
        return this;
    }

    public Vector3D copy(Vector3D vec) {
        this.x = vec.x;
        this.y = vec.y;
        this.z = vec.z;
        return this;
    }

    public double length() {
        return Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2) + Math.pow(this.z, 2));
    }

    public double lengthSquared() {
        return Math.pow(this.x, 2) + Math.pow(this.y, 2) + Math.pow(this.z, 2);
    }

    public double distance(Vector3D o) {
        return Math.sqrt(Math.pow(this.x - o.x, 2) + Math.pow(this.y - o.y, 2) + Math.pow(this.z - o.z, 2));
    }

    public double distanceSquared(Vector3D o) {
        return Math.pow(this.x - o.x, 2) + Math.pow(this.y - o.y, 2) + Math.pow(this.z - o.z, 2);
    }

    public float angle(Vector3D other) {
        double dot = this.dot(other) / (this.length() * other.length());
        return (float)Math.acos(dot);
    }

    public Vector3D midpoint(Vector3D other) {
        this.x = (this.x + other.x) / 2.0D;
        this.y = (this.y + other.y) / 2.0D;
        this.z = (this.z + other.z) / 2.0D;
        return this;
    }

    public Vector3D getMidpoint(Vector3D other) {
        double x = (this.x + other.x) / 2.0D;
        double y = (this.y + other.y) / 2.0D;
        double z = (this.z + other.z) / 2.0D;
        return new Vector3D(x, y, z);
    }

    public Vector3D multiply(int m) {
        this.x *= m;
        this.y *= m;
        this.z *= m;
        return this;
    }

    public Vector3D multiply(double m) {
        this.x *= m;
        this.y *= m;
        this.z *= m;
        return this;
    }

    public Vector3D multiply(float m) {
        this.x *= m;
        this.y *= m;
        this.z *= m;
        return this;
    }

    public double dot(Vector3D other) {
        return this.x * other.x + this.y * other.y + this.z * other.z;
    }

    public Vector3D crossProduct(Vector3D o) {
        double newX = this.y * o.z - o.y * this.z;
        double newY = this.z * o.x - o.z * this.x;
        double newZ = this.x * o.y - o.x * this.y;
        this.x = newX;
        this.y = newY;
        this.z = newZ;
        return this;
    }

    public Vector3D getCrossProduct(Vector3D o) {
        double x = this.y * o.z - o.y * this.z;
        double y = this.z * o.x - o.z * this.x;
        double z = this.x * o.y - o.x * this.y;
        return new Vector3D(x, y, z);
    }

    public Vector3D normalize() {
        double length = this.length();
        this.x /= length;
        this.y /= length;
        this.z /= length;
        return this;
    }

    public Vector3D zero() {
        this.x = 0.0D;
        this.y = 0.0D;
        this.z = 0.0D;
        return this;
    }

    public boolean isInAABB(Vector3D min, Vector3D max) {
        return this.x >= min.x && this.x <= max.x && this.y >= min.y && this.y <= max.y && this.z >= min.z && this.z <= max.z;
    }

    public boolean isInSphere(Vector3D origin, double radius) {
        return Math.pow(origin.x - this.x, 2) + Math.pow(origin.y - this.y, 2) + Math.pow(origin.z - this.z, 2) <= Math.pow(radius, 2);
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public double getZ() {
        return this.z;
    }

    public Vector3D setX(int x) {
        this.x = x;
        return this;
    }

    public Vector3D setX(double x) {
        this.x = x;
        return this;
    }

    public Vector3D setX(float x) {
        this.x = x;
        return this;
    }

    public Vector3D setY(int y) {
        this.y = y;
        return this;
    }

    public Vector3D setY(double y) {
        this.y = y;
        return this;
    }

    public Vector3D setY(float y) {
        this.y = y;
        return this;
    }

    public Vector3D setZ(int z) {
        this.z = z;
        return this;
    }

    public Vector3D setZ(double z) {
        this.z = z;
        return this;
    }

    public Vector3D setZ(float z) {
        this.z = z;
        return this;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Vector3D)) {
            return false;
        } else {
            Vector3D other = (Vector3D)obj;
            return Math.abs(this.x - other.x) < getEpsilon() && Math.abs(this.y - other.y) < getEpsilon() && Math.abs(this.z - other.z) < getEpsilon() && this.getClass().equals(obj.getClass());
        }
    }

    public int hashCode() {

        int hash = 79 * 7 + (int)(Double.doubleToLongBits(this.x) ^ Double.doubleToLongBits(this.x) >>> 32);
        hash = 79 * hash + (int)(Double.doubleToLongBits(this.y) ^ Double.doubleToLongBits(this.y) >>> 32);
        hash = 79 * hash + (int)(Double.doubleToLongBits(this.z) ^ Double.doubleToLongBits(this.z) >>> 32);
        return hash;
    }

    public Vector3D clone() {
        return new Vector3D(x, y, z);
    }

    public String toString() {
        return this.x + "," + this.y + "," + this.z;
    }

    public static double getEpsilon() {
        return 1.0E-6D;
    }

    public static Vector3D getMinimum(Vector3D v1, Vector3D v2) {
        return new Vector3D(Math.min(v1.x, v2.x), Math.min(v1.y, v2.y), Math.min(v1.z, v2.z));
    }

    public static Vector3D getMaximum(Vector3D v1, Vector3D v2) {
        return new Vector3D(Math.max(v1.x, v2.x), Math.max(v1.y, v2.y), Math.max(v1.z, v2.z));
    }
}
