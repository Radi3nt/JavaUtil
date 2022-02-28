package fr.radi3nt.maths.components.matrices;

public interface PerspectiveMatrix extends Matrix {

    Matrix perspective(float fov, float aspect, float near, float far);

}
