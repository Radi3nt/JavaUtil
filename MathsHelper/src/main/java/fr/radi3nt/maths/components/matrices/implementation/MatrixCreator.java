package fr.radi3nt.maths.components.matrices.implementation;

import fr.radi3nt.maths.components.matrices.Matrix;
import fr.radi3nt.maths.components.matrices.PerspectiveMatrix;
import fr.radi3nt.maths.components.matrices.ViewMatrix;

public class MatrixCreator {

    public static Matrix createMatrix() {
        return new ArrayMatrix();
    }

    public static PerspectiveMatrix createProjection() {
        return new ArrayMatrix();
    }

    public static ViewMatrix createView() {
        return new ArrayMatrix();
    }
}
