package fr.radi3nt.maths.dynamics;

import fr.radi3nt.maths.components.vectors.Vector3f;

public class DirectionRotationSmoothDynamics extends VectorSmoothDynamics<Vector3f> {

    public DirectionRotationSmoothDynamics(DynamicsConstants constants, Vector3f start) {
        super(constants, start);
    }

    @Override
    public void update(float step) {
        if (inputPrevious.dot(inputCurrent)<0) {
            inputCurrent.mul(-1);
        }

        super.update(step);
        response.normalizeSafely();
    }
}
