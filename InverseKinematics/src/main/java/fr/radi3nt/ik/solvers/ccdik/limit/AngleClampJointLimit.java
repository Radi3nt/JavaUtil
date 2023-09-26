package fr.radi3nt.ik.solvers.ccdik.limit;

import fr.radi3nt.ik.solvers.ccdik.constraint.HingeJointConstraint;
import fr.radi3nt.maths.Maths;
import fr.radi3nt.maths.components.advanced.matrix.angle.Angle;
import fr.radi3nt.maths.components.advanced.matrix.angle.JavaMathAngle;
import fr.radi3nt.maths.components.advanced.quaternions.ComponentsQuaternion;
import fr.radi3nt.maths.components.advanced.quaternions.Quaternion;
import fr.radi3nt.maths.components.vectors.Vector3f;

public class AngleClampJointLimit implements JointLimit {

    private final Angle minAngle;
    private final Angle maxAngle;
    private final Quaternion zeroAngle;
    private final Vector3f defaultAxis;

    public AngleClampJointLimit(Angle minAngle, Angle maxAngle, Quaternion zeroAngle, Vector3f defaultAxis) {
        this.minAngle = minAngle;
        this.maxAngle = maxAngle;
        this.zeroAngle = zeroAngle;
        this.defaultAxis = defaultAxis;
    }

    public AngleClampJointLimit(Angle minAngle, Angle maxAngle, HingeJointConstraint constraint) {
        this(minAngle, maxAngle, constraint.getConstrainedAxis());
    }

    public AngleClampJointLimit(Angle minAngle, Angle maxAngle, Vector3f axis) {
        this(minAngle, maxAngle, ComponentsQuaternion.fromAxisAndAngle(axis, JavaMathAngle.zero()), axis.duplicate());
    }

    @Override
    public void limit(Quaternion rotation) {

        double minAngleRadiant = minAngle.getRadiant();
        double maxAngleRadiant = maxAngle.getRadiant();

        double max = Math.max(maxAngleRadiant, minAngleRadiant);
        double min = Math.min(maxAngleRadiant, minAngleRadiant);

        Quaternion q1 = rotation.duplicate();
        Quaternion q2 = zeroAngle.duplicate();
        q1.inverse();
        q1.multiply(q2);

        double angle = (2 * Math.atan2(q1.getVector().length()+0f, q1.getW()));

        Vector3f axis = rotation.getAxisOrDefault(defaultAxis.duplicate());
        if (axis.dot(defaultAxis)<0) {
            angle=-angle;
            axis.negate();
        }

        angle = Maths.clamp(angle, min, max);

        Quaternion result = ComponentsQuaternion.fromAxisAndAngle(axis, JavaMathAngle.fromRadiant(angle));
        rotation.copy(result);
    }
}
