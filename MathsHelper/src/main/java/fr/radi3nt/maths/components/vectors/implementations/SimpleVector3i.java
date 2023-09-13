package fr.radi3nt.maths.components.vectors.implementations;

import fr.radi3nt.maths.components.vectors.Vector3f;
import fr.radi3nt.maths.components.vectors.Vector3i;

public class SimpleVector3i implements Vector3i {

    private int x;
    private int y;
    private int z;

    public SimpleVector3i(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public SimpleVector3i(Vector3i vector3f) {
        this(vector3f.getX(), vector3f.getY(), vector3f.getZ());
    }

    public SimpleVector3i() {
    }

    public SimpleVector3i(Vector3f position) {
        this.x = (int) Math.floor(position.getX());
        this.y = (int) Math.floor(position.getY());
        this.z = (int) Math.floor(position.getZ());
    }

    public static Vector3i fromRound(Vector3f start) {
        return new SimpleVector3i(Math.round(start.getX()), Math.round(start.getY()), Math.round(start.getZ()));
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public void setY(int y) {
        this.y = y;
    }

    @Override
    public int getZ() {
        return z;
    }

    @Override
    public void setZ(int z) {
        this.z = z;
    }

    @Override
    public void set(int x, int y, int z) {
        setX(x);
        setY(y);
        setZ(z);
    }

    @Override
    public void set(Vector3i original) {
        set(original.getX(), original.getY(), original.getZ());
    }

    @Override
    public Vector3i add(Vector3i vector3f) {
        return add(vector3f.getX(), vector3f.getY(), vector3f.getZ());
    }

    @Override
    public Vector3i add(int x, int y, int z) {
        this.setX(this.getX() + x);
        this.setY(this.getY() + y);
        this.setZ(this.getZ()+z);
        return this;
    }

    @Override
    public Vector3i sub(Vector3i vector2f) {
        return sub(vector2f.getX(), vector2f.getY(), vector2f.getZ());
    }

    @Override
    public Vector3i sub(int x, int y, int z) {
        this.setX(this.getX()-x);
        this.setY(this.getY()-y);
        this.setZ(this.getZ()-z);
        return this;
    }

    @Override
    public Vector3i mul(Vector3i vector2f) {
        return mul(vector2f.getX(), vector2f.getY(), vector2f.getZ());
    }

    @Override
    public Vector3i mul(int x, int y, int z) {
        this.setX(this.getX()*x);
        this.setY(this.getY()*y);
        this.setZ(this.getZ()*z);
        return this;
    }

    @Override
    public Vector3i mul(int mul) {
        return mul(mul, mul, mul);
    }

    @Override
    public Vector3i div(Vector3i vector2f) {
        return div(vector2f.getX(), vector2f.getY(), vector2f.getZ());
    }

    @Override
    public Vector3i div(int x, int y, int z) {
        this.setX(this.getX()/x);
        this.setY(this.getY() / y);
        this.setZ(this.getZ() / z);
        return this;
    }

    @Override
    public Vector3i div(int mul) {
        return div(mul, mul, mul);
    }

    @Override
    public Vector3i floorDiv(int mul) {
        this.setX(Math.floorDiv(this.getX(), mul));
        this.setY(Math.floorDiv(this.getY(), mul));
        this.setZ(Math.floorDiv(this.getZ(), mul));
        return this;
    }

    @Override
    public Vector3i clone() {
        return new SimpleVector3i(x, y, z);
    }

    @Override
    public Vector3i cross(Vector3i vector3f) {
        int x = y * vector3f.getZ() - z * vector3f.getY();
        int y = z * vector3f.getX() - this.x * vector3f.getZ();
        int z = this.x * vector3f.getY() - this.y * vector3f.getX();

        setX(x);
        setY(y);
        setZ(z);

        return this;
    }

    @Override
    public float dot(Vector3i vector2f) {
        return x * vector2f.getX() + y * vector2f.getY() + z * vector2f.getZ();
    }

    @Override
    public Vector3i normalize() {
        return div((int) length());
    }

    @Override
    public Vector3i negate() {
        this.x = -this.x;
        this.y = -this.y;
        this.z = -this.z;
        return this;
    }

    @Override
    public float length() {
        return (int) Math.sqrt(x * x + y * y + z * z);
    }

    @Override
    public String toString() {
        return "SimpleVector3f{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SimpleVector3i that = (SimpleVector3i) o;
        return that.x == x && that.y == y && that.z == z;
    }

    @Override
    public int hashCode() {
        int seed = 3;
        {
            seed = getSeed(this.x, seed);
            seed = getSeed(this.y, seed);
            seed = getSeed(this.z, seed);
        }
        return seed;
    }

    private static int getSeed(int number, int seed) {
        int x = ((number >> 16) ^ number) * 0x45d9f3b;
        x = ((x >> 16) ^ x) * 0x45d9f3b;
        x = (x >> 16) ^ x;
        seed ^= x + 0x9e3779b9 + (seed << 6) + (seed >> 2);
        return seed;
    }
}
