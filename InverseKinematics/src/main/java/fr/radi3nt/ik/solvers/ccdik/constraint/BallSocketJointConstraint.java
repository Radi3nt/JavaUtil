package fr.radi3nt.ik.solvers.ccdik.constraint;

import fr.radi3nt.ik.solvers.ccdik.CCDJoint;
import fr.radi3nt.maths.components.advanced.matrix.angle.Angle;
import fr.radi3nt.maths.components.advanced.matrix.angle.JavaMathAngle;
import fr.radi3nt.maths.components.advanced.quaternions.ComponentsQuaternion;
import fr.radi3nt.maths.components.advanced.quaternions.Quaternion;
import fr.radi3nt.maths.components.vectors.Vector3f;
import fr.radi3nt.maths.components.vectors.implementations.SimpleVector3f;

public class BallSocketJointConstraint implements JointConstraint {

    private final Vector3f upAxis;
    private final Vector3f constrainingAxis;
    private final Angle limit;

    public BallSocketJointConstraint(Vector3f upAxis, Vector3f constrainingAxis, Angle limit) {
        this.upAxis = upAxis;
        this.constrainingAxis = constrainingAxis;
        this.limit = limit;
    }

    @Override
    public void constrain(CCDJoint ccdJoint) {
        Quaternion toUpQuaternion = ComponentsQuaternion.fromTwoVectors(constrainingAxis, upAxis);
        Quaternion toAxisFromUpQuaternion = toUpQuaternion.duplicate();
        toAxisFromUpQuaternion.inverse();

        Quaternion rot = ccdJoint.rotation.duplicate();
        rot.multiply(toUpQuaternion);
        Angle angle = rot.getAngle();
        double rad = Math.min(angle.getRadiant(), limit.getRadiant());
        Angle constrainedAngle = JavaMathAngle.fromRadiant(rad);

        Quaternion newRotation = ComponentsQuaternion.fromAxisAndAngle(rot.getAxisOrDefault(new SimpleVector3f(0, 1, 0)), constrainedAngle);
        newRotation.multiply(toAxisFromUpQuaternion);

        ccdJoint.rotation = newRotation;

    }
}
