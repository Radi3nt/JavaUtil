package fr.radi3nt.ik.solvers.fabrik.chain;

import fr.radi3nt.maths.components.advanced.quaternions.Quaternion;
import fr.radi3nt.maths.components.vectors.Vector3f;

public class IkJointTransform {

    private final Vector3f resultPosition;
    private final Quaternion resultRotation;

    public IkJointTransform(Vector3f resultPosition, Quaternion resultRotation) {
        this.resultPosition = resultPosition;
        this.resultRotation = resultRotation;
    }

    public Vector3f getResultPosition() {
        return resultPosition;
    }

    public Quaternion getResultRotation() {
        return resultRotation;
    }
}
