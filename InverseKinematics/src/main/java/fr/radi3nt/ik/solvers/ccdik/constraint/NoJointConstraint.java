package fr.radi3nt.ik.solvers.ccdik.constraint;

import fr.radi3nt.maths.components.advanced.quaternions.Quaternion;

public class NoJointConstraint implements JointConstraint {

    public static final NoJointConstraint INSTANCE = new NoJointConstraint();

    private NoJointConstraint() {

    }

    @Override
    public void constrain(Quaternion rotation) {

    }
}
