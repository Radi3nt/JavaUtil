package fr.radi3nt.ik.solvers.ccdik;

import fr.radi3nt.ik.solvers.ccdik.constraint.JointConstraint;
import fr.radi3nt.ik.solvers.ccdik.limit.JointLimit;
import fr.radi3nt.maths.components.advanced.quaternions.ComponentsQuaternion;
import fr.radi3nt.maths.components.advanced.quaternions.Quaternion;

public class CCDJoint {

    private final float length;
    public Quaternion baseRot = ComponentsQuaternion.zero();
    public Quaternion rotation = ComponentsQuaternion.zero();

    private final JointConstraint jointConstraint;
    private final JointLimit jointLimit;

    public CCDJoint(float length, JointConstraint jointConstraint, JointLimit jointLimit) {
        this.length = length;
        this.jointConstraint = jointConstraint;
        this.jointLimit = jointLimit;
    }

    public CCDJoint(float length, JointConstraint jointConstraint, JointLimit jointLimit, Quaternion baseRot) {
        this.length = length;
        this.jointConstraint = jointConstraint;
        this.jointLimit = jointLimit;
        this.baseRot = baseRot.duplicate();
    }

    public void constrain() {
        jointConstraint.constrain(this.rotation);
    }

    public void limit() {
        jointLimit.limit(this.rotation);
    }

    public float getLength() {
        return length;
    }
}
