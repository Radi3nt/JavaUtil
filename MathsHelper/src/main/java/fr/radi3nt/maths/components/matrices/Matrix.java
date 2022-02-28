package fr.radi3nt.maths.components.matrices;

import fr.radi3nt.maths.components.vectors.Vector3f;
import fr.radi3nt.maths.components.vectors.Vector4f;

import java.nio.FloatBuffer;

public interface Matrix {

    Matrix identity();
    Matrix zero();

    Matrix translation(Vector3f vector3f);

    /**
     * @param value Angle in radians
     */
    Matrix rotation(float value, Vector3f axis);
    /**
     * @param rotation Angles in radians
     */
    Matrix rotation(Vector3f rotation);
    Matrix scaling(Vector3f scale);

    Matrix translate(Vector3f vector3f);
    /**
     * @param value Angle in radians
     */
    Matrix rotate(float value, Vector3f axis);
    Matrix scale(Vector3f scale);

    Matrix invert();
    Matrix transpose();

    Vector4f transform(Vector4f vector4f);

    Matrix multiply(Matrix matrix);
    Matrix mul(Matrix matrix);

    Matrix set(int x, int y, float value);
    float get(int x, int y);

    Matrix store(FloatBuffer buffer);

    Matrix duplicate();
}
