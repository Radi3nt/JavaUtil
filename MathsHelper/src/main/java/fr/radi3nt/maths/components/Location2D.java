package fr.radi3nt.maths.components;

import java.util.Objects;

public class Location2D {

    private double x;
    private double y;

    public Location2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Location2D add(Vector2D vector) {
        addX(vector.getX());
        addY(vector.getY());
        return this;
    }

    public Location2D addX(double xToAdd) {
        x += xToAdd;
        return this;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getX() {
        return x;
    }

    public Location2D addY(double yToAdd) {
        y += yToAdd;
        return this;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getY() {
        return y;
    }

    public int distanceApproach(Location2D location2D) {
        return (int) (Math.abs(this.x - location2D.x) + Math.abs(this.y - location2D.y));
    }

    public Location2D duplicate() {
        return new Location2D(getX(), getY());
    }

    @Override
    public String toString() {
        return "Location{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Location2D)) return false;
        Location2D location2D = (Location2D) o;
        return Double.compare(location2D.getX(), getX()) == 0 && Double.compare(location2D.getY(), getY()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getX(), getY());
    }
}
