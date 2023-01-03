package fr.radi3nt.maths.components;

import java.util.Map;

public class Vector2D implements Cloneable {

    private static final double epsilon = 0.000001;

    protected double x;
    protected double y;

    /**
     * Construct the vector with all components as 0.
     */
    public Vector2D() {
        this.x = 0;
        this.y = 0;
    }

    /**
     * Construct the vector with provided integer components.
     *
     * @param x X component
     * @param y Y component
     */
    public Vector2D(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Construct the vector with provided double components.
     *
     * @param x X component
     * @param y Y component
     */
    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Construct the vector with provided float components.
     *
     * @param x X component
     * @param y Y component
     */
    public Vector2D(float x, float y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Gets the minimum components of two vectors.
     *
     * @param v1 The first vector.
     * @param v2 The second vector.
     * @return minimum
     */
    public static Vector2D getMinimum(Vector2D v1, Vector2D v2) {
        return new Vector2D(Math.min(v1.x, v2.x), Math.min(v1.y, v2.y));
    }

    /**
     * Gets the maximum components of two vectors.
     *
     * @param v1 The first vector.
     * @param v2 The second vector.
     * @return maximum
     */
    public static Vector2D getMaximum(Vector2D v1, Vector2D v2) {
        return new Vector2D(Math.max(v1.x, v2.x), Math.max(v1.y, v2.y));
    }

    public static Vector2D deserialize(Map<String, Object> args) {
        double x = 0;
        double y = 0;

        if (args.containsKey("x")) {
            x = (Double) args.get("x");
        }
        if (args.containsKey("y")) {
            y = (Double) args.get("y");
        }

        return new Vector2D(x, y);
    }

    /**
     * Adds a vector to this one
     *
     * @param vec The other vector
     * @return the same vector
     */
    public Vector2D add(Vector2D vec) {
        x += vec.x;
        y += vec.y;
        return this;
    }

    /**
     * Subtracts a vector from this one.
     *
     * @param vec The other vector
     * @return the same vector
     */
    public Vector2D subtract(Vector2D vec) {
        x -= vec.x;
        y -= vec.y;
        return this;
    }

    /**
     * Gets the magnitude of the vector, defined as sqrt(x^2+y^2). The
     * value of this method is not cached and uses a costly square-root
     * function, so do not repeatedly call this method to get the vector's
     * magnitude. NaN will be returned if the inner result of the sqrt()
     * function overflows, which will be caused if the length is too long.
     *
     * @return the magnitude
     */
    public double length() {
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }

    /**
     * Gets the magnitude of the vector squared.
     *
     * @return the magnitude
     */
    public double lengthSquared() {
        return Math.sqrt(x) + Math.sqrt(y);
    }

    /**
     * Multiplies the vector by another.
     *
     * @param vec The other vector
     * @return the same vector
     */
    public Vector2D multiply(Vector2D vec) {
        x *= vec.x;
        y *= vec.y;
        return this;
    }

    /**
     * Divides the vector by another.
     *
     * @param vec The other vector
     * @return the same vector
     */
    public Vector2D divide(Vector2D vec) {
        x /= vec.x;
        y /= vec.y;
        return this;
    }

    /**
     * Copies another vector
     *
     * @param vec The other vector
     * @return the same vector
     */
    public Vector2D copy(Vector2D vec) {
        x = vec.x;
        y = vec.y;
        return this;
    }

    /**
     * Get the distance between this vector and another. The value of this
     * method is not cached and uses a costly square-root function, so do not
     * repeatedly call this method to get the vector's magnitude. NaN will be
     * returned if the inner result of the sqrt() function overflows, which
     * will be caused if the distance is too long.
     *
     * @param o The other vector
     * @return the distance
     */
    public double distance(Vector2D o) {
        return Math.sqrt(Math.pow(x - o.x, 2) + Math.pow(y - o.y, 2));
    }

    /**
     * Get the squared distance between this vector and another.
     *
     * @param o The other vector
     * @return the distance
     */
    public double distanceSquared(Vector2D o) {
        return Math.sqrt(x - o.x) + Math.sqrt(y - o.y);
    }

    /**
     * Gets the angle between this vector and another in radians.
     *
     * @param other The other vector
     * @return angle in radians
     */
    public float angle(Vector2D other) {
        double dot = dot(other) / (length() * other.length());

        return (float) Math.acos(dot);
    }

    /**
     * Sets this vector to the midpoint between this vector and another.
     *
     * @param other The other vector
     * @return this same vector (now a midpoint)
     */
    public Vector2D midpoint(Vector2D other) {
        x = (x + other.x) / 2;
        y = (y + other.y) / 2;
        return this;
    }

    /**
     * Gets a new midpoint vector between this vector and another.
     *
     * @param other The other vector
     * @return a new midpoint vector
     */
    public Vector2D getMidpoint(Vector2D other) {
        double x = (this.x + other.x) / 2;
        double y = (this.y + other.y) / 2;
        return new Vector2D(x, y);
    }

    /**
     * Performs scalar multiplication, multiplying all components with a
     * scalar.
     *
     * @param m The factor
     * @return the same vector
     */
    public Vector2D multiply(int m) {
        x *= m;
        y *= m;
        return this;
    }

    /**
     * Performs scalar multiplication, multiplying all components with a
     * scalar.
     *
     * @param m The factor
     * @return the same vector
     */
    public Vector2D multiply(double m) {
        x *= m;
        y *= m;
        return this;
    }

    /**
     * Performs scalar multiplication, multiplying all components with a
     * scalar.
     *
     * @param m The factor
     * @return the same vector
     */
    public Vector2D multiply(float m) {
        x *= m;
        y *= m;
        return this;
    }

    /**
     * Calculates the dot product of this vector with another. The dot product
     * is defined as x1*x2+y1*y2+z1*z2. The returned value is a scalar.
     *
     * @param other The other vector
     * @return dot product
     */
    public double dot(Vector2D other) {
        return x * other.x + y * other.y;
    }

    /**
     * Gets the X component.
     *
     * @return The X component.
     */
    public double getX() {
        return x;
    }


    /**
     * Gets the Y component.
     *
     * @return The Y component.
     */
    public double getY() {
        return y;
    }

    /**
     * Converts this vector to a unit vector (a vector with length of 1).
     *
     * @return the same vector
     */
    public Vector2D normalize() {
        double length = length();

        x /= length;
        y /= length;

        return this;
    }

    /**
     * Zero this vector's components.
     *
     * @return the same vector
     */
    public Vector2D zero() {
        x = 0;
        y = 0;
        return this;
    }

    /**
     * Returns whether this vector is in an axis-aligned bounding box.
     * <p>
     * The minimum and maximum vectors given must be truly the minimum and
     * maximum X, Y and Z components.
     *
     * @param min Minimum vector
     * @param max Maximum vector
     * @return whether this vector is in the AABB
     */
    public boolean isInAABB(Vector2D min, Vector2D max) {
        return x >= min.x && x <= max.x && y >= min.y && y <= max.y;
    }

    /**
     * Set the X component.
     *
     * @param x The new X component.
     * @return This vector.
     */
    public Vector2D setX(int x) {
        this.x = x;
        return this;
    }

    /**
     * Set the X component.
     *
     * @param x The new X component.
     * @return This vector.
     */
    public Vector2D setX(double x) {
        this.x = x;
        return this;
    }

    /**
     * Set the X component.
     *
     * @param x The new X component.
     * @return This vector.
     */
    public Vector2D setX(float x) {
        this.x = x;
        return this;
    }

    /**
     * Set the Y component.
     *
     * @param y The new Y component.
     * @return This vector.
     */
    public Vector2D setY(int y) {
        this.y = y;
        return this;
    }

    /**
     * Set the Y component.
     *
     * @param y The new Y component.
     * @return This vector.
     */
    public Vector2D setY(double y) {
        this.y = y;
        return this;
    }

    /**
     * Set the Y component.
     *
     * @param y The new Y component.
     * @return This vector.
     */
    public Vector2D setY(float y) {
        this.y = y;
        return this;
    }

    public Vector2D duplicate() {
        return new Vector2D(getX(), getY());
    }

    /**
     * Returns a hash code for this vector
     *
     * @return hash code
     */
    @Override
    public int hashCode() {
        int hash = 7;

        hash = 79 * hash + (int) (Double.doubleToLongBits(this.x) ^ (Double.doubleToLongBits(this.x) >>> 32));
        hash = 79 * hash + (int) (Double.doubleToLongBits(this.y) ^ (Double.doubleToLongBits(this.y) >>> 32));
        return hash;
    }


    /**
     * Returns this vector's components as x,y,z.
     */
    @Override
    public String toString() {
        return x + ";" + y;
    }

    /**
     * Get the threshold used for equals().
     *
     * @return The epsilon.
     */
    public static double getEpsilon() {
        return epsilon;
    }

    /**
     * Checks to see if two objects are equal.
     * <p>
     * Only two Vectors can ever return true. This method uses a fuzzy match
     * to account for floating point errors. The epsilon can be retrieved
     * with epsilon.
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Vector2D)) {
            return false;
        }

        Vector2D other = (Vector2D) obj;

        return Math.abs(x - other.x) < epsilon && Math.abs(y - other.y) < epsilon && (this.getClass().equals(obj.getClass()));
    }

    /**
     * Get a new vector.
     *
     * @return vector
     */
    @Override
    public Vector2D clone() {
        try {
            return (Vector2D) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new Error(e);
        }
    }
}
