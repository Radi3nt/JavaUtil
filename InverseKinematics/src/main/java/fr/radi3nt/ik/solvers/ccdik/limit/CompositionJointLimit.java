package fr.radi3nt.ik.solvers.ccdik.limit;

import fr.radi3nt.ik.solvers.ccdik.CCDJoint;

public class CompositionJointLimit implements JointLimit {

    private final JointLimit[] jointLimits;

    public CompositionJointLimit(JointLimit... jointLimits) {
        this.jointLimits = jointLimits;
    }


    @Override
    public void limit(CCDJoint ccdJoint) {
        for (JointLimit jointLimit : jointLimits) {
            jointLimit.limit(ccdJoint);
        }
    }
}
