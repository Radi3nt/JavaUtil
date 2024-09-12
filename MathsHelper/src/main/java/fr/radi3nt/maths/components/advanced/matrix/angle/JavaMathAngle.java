package fr.radi3nt.maths.components.advanced.matrix.angle;

public class JavaMathAngle implements Angle {

    public static final Angle POSITIVE_RIGHT_ANGLE = JavaMathAngle.fromDegree(90);
    public static final Angle NEAGTIVE_RIGHT_ANGLE = JavaMathAngle.fromDegree(-90);

    private double angleValueInRadiant;

    private JavaMathAngle(double angleValueInRadiant) {
        this.angleValueInRadiant = angleValueInRadiant;
    }

    public static JavaMathAngle fromDegree(double angleInDegree) {
        return new JavaMathAngle(Math.toRadians(angleInDegree));
    }

    public static JavaMathAngle fromRadiant(double angleInRadiant) {
        return new JavaMathAngle(angleInRadiant);
    }

    public static JavaMathAngle zero() {
        return new JavaMathAngle(0);
    }

    @Override
    public float cos() {
        return (float) Math.cos(angleValueInRadiant);
    }

    @Override
    public float sin() {
        return (float) Math.sin(angleValueInRadiant);
    }

    @Override
    public double getRadiant() {
        return angleValueInRadiant;
    }

    @Override
    public void setRadiant(double radiant) {
        angleValueInRadiant = radiant;
    }

    @Override
    public double getDegree() {
        return Math.toDegrees(angleValueInRadiant);
    }

    @Override
    public void setDegree(double degree) {
        angleValueInRadiant = Math.toRadians(degree);
    }

    @Override
    public String toString() {
        return "JavaMathAngle{" +
                "angleValueInRadiant=" + angleValueInRadiant +
                '}';
    }
}
