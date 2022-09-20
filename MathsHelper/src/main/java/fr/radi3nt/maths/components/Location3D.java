package fr.radi3nt.maths.components;

public class Location3D {
    
    private double x;
    private double y;
    private double z;

    public Location3D(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getX() {
        return this.x;
    }
    
    public void setY(double y) {
        this.y = y;
    }

    public double getY() {
        return this.y;
    }
    
    public void setZ(double z) {
        this.z = z;
    }

    public double getZ() {
        return this.z;
    }
    
    public Location3D add(Location3D vec) {
        if (vec != null) {
            this.x += vec.x;
            this.y += vec.y;
            this.z += vec.z;
            return this;
        } else {
            throw new IllegalArgumentException("Cannot add Locations of differing worlds");
        }
    }

    public Location3D add(Vector3D vec) {
        this.x += vec.getX();
        this.y += vec.getY();
        this.z += vec.getZ();
        return this;
    }

    public Location3D add(double x, double y, double z) {
        this.x += x;
        this.y += y;
        this.z += z;
        return this;
    }

    public Location3D subtract(Location3D vec) {
        if (vec != null) {
            this.x -= vec.x;
            this.y -= vec.y;
            this.z -= vec.z;
            return this;
        } else {
            throw new IllegalArgumentException("Cannot add Locations of differing worlds");
        }
    }

    public Location3D subtract(Vector3D vec) {
        this.x -= vec.getX();
        this.y -= vec.getY();
        this.z -= vec.getZ();
        return this;
    }

    public Location3D subtract(double x, double y, double z) {
        this.x -= x;
        this.y -= y;
        this.z -= z;
        return this;
    }

    public double length() {
        return Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2) + Math.pow(this.z, 2));
    }

    public double lengthSquared() {
        return Math.pow(this.x, 2) + Math.pow(this.y, 2) + Math.pow(this.z, 2);
    }

    public double distance(Location3D o) {
        return Math.sqrt(this.distanceSquared(o));
    }

    public double distanceSquared(Location3D o) {
        if (o == null) {
            throw new IllegalArgumentException("Cannot measure distance to a null location");
        } else {
            return Math.pow(this.x - o.x, 2) + Math.pow(this.y - o.y, 2) + Math.pow(this.z - o.z, 2);
        }
    }

    public Location3D multiply(double m) {
        this.x *= m;
        this.y *= m;
        this.z *= m;
        return this;
    }

    public Location3D zero() {
        this.x = 0.0;
        this.y = 0.0;
        this.z = 0.0;
        return this;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        } else if (this.getClass() != obj.getClass()) {
            return false;
        } else {
            Location3D other = (Location3D)obj;
            if (Double.doubleToLongBits(this.x) != Double.doubleToLongBits(other.x)) {
                return false;
            } else if (Double.doubleToLongBits(this.y) != Double.doubleToLongBits(other.y)) {
                return false;
            } else
                return Double.doubleToLongBits(this.z) == Double.doubleToLongBits(other.z);
        }
    }

    public int hashCode() {
        int hash = 3;
        hash = 19 * hash + (int)(Double.doubleToLongBits(this.x) ^ Double.doubleToLongBits(this.x) >>> 32);
        hash = 19 * hash + (int)(Double.doubleToLongBits(this.y) ^ Double.doubleToLongBits(this.y) >>> 32);
        hash = 19 * hash + (int)(Double.doubleToLongBits(this.z) ^ Double.doubleToLongBits(this.z) >>> 32);
        return hash;
    }

    public String toString() {
        return "Location{" + "x=" + this.x + ",y=" + this.y + ",z=" + this.z + '}';
    }

    public Vector3D toVector() {
        return new Vector3D(this.x, this.y, this.z);
    }

    @Override
    public Location3D clone() {
        return new Location3D(x, y, z);
    }
}
