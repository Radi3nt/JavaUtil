package fr.radi3nt.ik.solvers.ccdik.constraint;

import fr.radi3nt.ik.solvers.ccdik.CCDJoint;

public interface JointConstraint {
    void constrain(CCDJoint ccdJoint);
}
