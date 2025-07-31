package fr.radi3nt.ik.solvers.ccdik.constraint;

import fr.radi3nt.maths.components.advanced.quaternions.Quaternion;

public interface JointConstraint {
    void constrain(Quaternion rotation);
}
