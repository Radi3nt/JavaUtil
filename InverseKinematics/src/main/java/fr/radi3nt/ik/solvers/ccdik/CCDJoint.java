package fr.radi3nt.ik.solvers.ccdik;

import fr.radi3nt.ik.solvers.ccdik.constraint.JointConstraint;
import fr.radi3nt.ik.solvers.ccdik.limit.JointLimit;
import fr.radi3nt.maths.components.advanced.quaternions.ComponentsQuaternion;
import fr.radi3nt.maths.components.advanced.quaternions.Quaternion;

public class CCDJoint {

    private final float length;
    public Quaternion rotation = ComponentsQuaternion.zero();

    private final JointConstraint jointConstraint;
    private final JointLimit jointLimit;

    public CCDJoint(float length, JointConstraint jointConstraint, JointLimit jointLimit) {
        this.length = length;
        this.jointConstraint = jointConstraint;
        this.jointLimit = jointLimit;
    }

    public void constrain() {
        jointConstraint.constrain(this);
    }

    public void limit() {
        jointLimit.limit(this.rotation);
    }

    public float getLength() {
        return length;
    }
}
