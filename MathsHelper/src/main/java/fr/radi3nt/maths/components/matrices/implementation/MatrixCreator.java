package fr.radi3nt.maths.components.matrices.implementation;

import fr.radi3nt.maths.components.matrices.Matrix;
import fr.radi3nt.maths.components.matrices.ProjectionMatrix;
import fr.radi3nt.maths.components.matrices.ViewMatrix;

public class MatrixCreator {

    public static Matrix createMatrix() {
        return new ArrayMatrix();
    }

    public static ProjectionMatrix createProjection() {
        return new ArrayMatrix();
    }

    public static ViewMatrix createView() {
        return new ArrayMatrix();
    }
}
