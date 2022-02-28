package fr.radi3nt.maths.components.matrices;

import fr.radi3nt.maths.components.vectors.Vector3f;

public interface ViewMatrix extends Matrix {

    ViewMatrix view(Vector3f position, Vector3f rotation);

}
