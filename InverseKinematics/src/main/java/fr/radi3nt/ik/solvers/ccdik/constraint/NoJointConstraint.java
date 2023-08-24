package fr.radi3nt.ik.solvers.ccdik.constraint;

import fr.radi3nt.ik.solvers.ccdik.CCDJoint;

public class NoJointConstraint implements JointConstraint {

    public static NoJointConstraint INSTANCE = new NoJointConstraint();

    private NoJointConstraint() {

    }

    @Override
    public void constrain(CCDJoint ccdJoint) {

    }
}
