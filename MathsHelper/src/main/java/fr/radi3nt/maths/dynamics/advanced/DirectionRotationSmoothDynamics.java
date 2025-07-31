package fr.radi3nt.maths.dynamics.advanced;

import fr.radi3nt.maths.components.vectors.Vector3f;

public class DirectionRotationSmoothDynamics extends VectorSmoothDynamics<Vector3f> {

    public DirectionRotationSmoothDynamics(DynamicsConstants constants, Vector3f start) {
        super(constants, start);
    }

    @Override
    public void update(float step) {
        super.update(step);
        response.normalizeSafely();
    }
}
