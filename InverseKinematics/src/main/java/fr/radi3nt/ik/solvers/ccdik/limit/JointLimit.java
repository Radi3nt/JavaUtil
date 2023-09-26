package fr.radi3nt.ik.solvers.ccdik.limit;

import fr.radi3nt.maths.components.advanced.quaternions.Quaternion;

public interface JointLimit {

    void limit(Quaternion ccdJoint);

}
