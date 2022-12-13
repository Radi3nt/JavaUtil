package fr.radi3nt.maths.components.advanced.matrix;

import fr.radi3nt.maths.components.advanced.matrix.angle.Angle;
import fr.radi3nt.maths.components.advanced.quaternions.Quaternion;
import fr.radi3nt.maths.components.vectors.Vector3f;
import fr.radi3nt.maths.components.vectors.Vector4f;

public interface Matrix4x4 extends Matrix {

    Quaternion getRotation();

    Vector3f getTranslation();

    Vector3f getScale();

    void translation(Vector3f translation);

    void scale(Vector3f scale);

    void rotationX(Angle angle);

    void rotationY(Angle angle);

    void rotationZ(Angle angle);

    void eulerRotation(Angle x, Angle y, Angle z);

    void quaternionRotation(Quaternion quaternion);

    void directionRotation(Vector3f direction, Vector3f up);

    void transform(Vector4f toTransform);

    void multiply(Matrix matrix);

    void copy(Matrix result);

    Matrix4x4 duplicate();

}
