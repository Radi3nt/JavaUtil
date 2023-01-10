package fr.radi3nt.maths.components.matrices;

public interface ProjectionMatrix extends Matrix {

    Matrix perspective(float fov, float aspect, float near, float far);
    Matrix orthographic(float right, float left, float top, float bottom, float near, float far);

}
