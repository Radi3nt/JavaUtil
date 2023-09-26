package fr.radi3nt.ik.solvers.ccdik.limit;

import fr.radi3nt.maths.components.advanced.quaternions.Quaternion;

public class CompositionJointLimit implements JointLimit {

    private final JointLimit[] jointLimits;

    public CompositionJointLimit(JointLimit... jointLimits) {
        this.jointLimits = jointLimits;
    }


    @Override
    public void limit(Quaternion ccdJoint) {
        for (JointLimit jointLimit : jointLimits) {
            jointLimit.limit(ccdJoint);
        }
    }
}
