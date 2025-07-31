package fr.radi3nt.ik.solvers.ccdik.constraint;

import fr.radi3nt.ik.solvers.ccdik.CCDJoint;
import fr.radi3nt.maths.components.advanced.quaternions.ComponentsQuaternion;
import fr.radi3nt.maths.components.advanced.quaternions.Quaternion;
import fr.radi3nt.maths.components.vectors.Vector3f;

public class HingeJointConstraint implements JointConstraint {

    private final Vector3f constrainedAxis;

    public HingeJointConstraint(Vector3f constrainedAxis) {
        this.constrainedAxis = constrainedAxis;
    }

    @Override
    public void constrain(Quaternion rotation) {
        Quaternion invRot = rotation.duplicate();
        invRot.inverse();

        Vector3f parentAxis = constrainedAxis.duplicate();
        invRot.transform(parentAxis);

        Quaternion quaternion = ComponentsQuaternion.fromTwoVectors(constrainedAxis, parentAxis);
        rotation.multiply(quaternion);
    }

    public Vector3f getConstrainedAxis() {
        return constrainedAxis;
    }
}
