package fr.radi3nt.maths.components.advanced.matrix;

import fr.radi3nt.maths.components.advanced.matrix.angle.Angle;
import fr.radi3nt.maths.components.advanced.quaternions.Quaternion;
import fr.radi3nt.maths.components.vectors.Vector3f;

public interface Matrix3x3 extends Matrix {

    Quaternion getRotation();

    void add(Matrix3x3 rotation);

    void scale(Vector3f scale);

    void rotationX(Angle angle);

    void rotationY(Angle angle);

    void rotationZ(Angle angle);

    void eulerRotation(Angle x, Angle y, Angle z);

    void quaternionRotation(Quaternion quaternion);

    void directionRotation(Vector3f direction, Vector3f up);

    void transform(Vector3f toTransform);

    void multiply(Matrix matrix);

    void copy(Matrix result);

    Matrix3x3 duplicate();

    void subtract(Matrix3x3 skewRJ);

    boolean isZero();
}
