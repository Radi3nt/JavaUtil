package fr.radi3nt.ik.solvers.ccdik;

import fr.radi3nt.ik.solvers.IkIterativeSolver;
import fr.radi3nt.ik.solvers.ccdik.effectors.Effector;
import fr.radi3nt.maths.components.advanced.quaternions.ComponentsQuaternion;
import fr.radi3nt.maths.components.advanced.quaternions.Quaternion;
import fr.radi3nt.maths.components.vectors.Vector3f;
import fr.radi3nt.maths.components.vectors.implementations.SimpleVector3f;

public class CCDIKSolver extends IkIterativeSolver {

    private final Effector endEffector;
    private final CCDJoint[] joints;
    private final Vector3f upAxis;
    private Vector3f goal;

    private Vector3f lastEndEffectorWorldPosition = null;

    public CCDIKSolver(int maxIterations, float allowedMarginOfError, Vector3f goal, Effector endEffector, Vector3f upAxis, CCDJoint[] joints) {
        super(maxIterations, allowedMarginOfError);
        this.goal = goal;
        this.endEffector = endEffector;
        this.upAxis = upAxis;
        this.joints = joints;
    }

    @Override
    protected boolean isInAllowedMarginOfError() {
        return evaluateMarginOfError() <= allowedMarginOfError*allowedMarginOfError;
    }

    protected float evaluateMarginOfError() {
        if (lastEndEffectorWorldPosition == null)
            lastEndEffectorWorldPosition = endEffector.getWorldPosition();
        return lastEndEffectorWorldPosition.duplicate().sub(goal).lengthSquared();
    }

    @Override
    protected void iteration() {
        for (int i = getEndJointIndex(); i >= 0; i--) {
            CCDJoint joint = joints[i];
            Quaternion fromToQuat = getRotationBetweenEndEffectorAndGoalUsingWorldSpace(joint, i);
            joint.rotation.multiply(fromToQuat);
            joint.constrain();
            joint.limit();
        }
    }

    private Quaternion getRotationBetweenEndEffectorAndGoalUsingWorldSpace(CCDJoint joint, int i) {
        Vector3f ei = lastEndEffectorWorldPosition==null ? endEffector.getWorldPosition().duplicate() : lastEndEffectorWorldPosition.duplicate();
        Vector3f et = goal.duplicate();
        lastEndEffectorWorldPosition = null;

        Vector3f bonePos = getBonePos(i);
        Quaternion invLinkRot = getAllPreviousRotation(i+1);
        invLinkRot.inverse();

        Vector3f localEi = ei.duplicate().sub(bonePos);
        invLinkRot.transform(localEi);
        localEi.normalize();
        Vector3f localEt = et.duplicate().sub(bonePos);
        invLinkRot.transform(localEt);
        localEt.normalize();

        Quaternion quaternion = ComponentsQuaternion.fromTwoVectors(localEi, localEt);
        return quaternion;
    }

    private Vector3f getBonePos(int index) {
        Quaternion cumulatedRotation = ComponentsQuaternion.zero();
        Vector3f worldPos = new SimpleVector3f();
        for (int i = 0; i < index; i++) {
            CCDJoint joint = joints[i];
            Quaternion rotation = joint.rotation;
            float length = joint.getLength();

            cumulatedRotation.multiply(rotation);
            Vector3f localTranslation = upAxis.duplicate();
            cumulatedRotation.transform(localTranslation);
            localTranslation.mul(length);
            worldPos.add(localTranslation);
        }
        return worldPos;
    }

    private Quaternion getRotationBetweenEndEffectorAndGoalUsingFabrikVersion(CCDJoint joint, int index) {
        Vector3f startPos = endEffector.getWorldPosition().duplicate();
        Vector3f endPos = goal.duplicate();
        Vector3f dirEndToStart = startPos.duplicate().sub(endPos);
        dirEndToStart.normalize();
        dirEndToStart.mul(joint.getLength());
        Quaternion quaternion = ComponentsQuaternion.fromVectorToAnother(new SimpleVector3f(0, 0, -1), dirEndToStart);

        Quaternion localSpaceRotation = getAllPreviousRotation(index);
        localSpaceRotation.inverse();
        localSpaceRotation.multiply(quaternion);
        return localSpaceRotation;
    }

    private Quaternion getRotationBetweenEndEffectorAndGoalUsingAdaptedVersion(CCDJoint joint, int index) {
        Vector3f tipPosition = endEffector.getWorldPosition();

        Vector3f dif = tipPosition.duplicate().duplicate().sub(goal);
        dif.normalize();

        Quaternion allPrevious = getAllPreviousRotation(index);
        allPrevious.inverse();
        allPrevious.transform(dif);

        return ComponentsQuaternion.fromVectorToAnother(new SimpleVector3f(0, 0, 1), dif);
    }


    public void setGoal(Vector3f goal) {
        this.goal = goal;
    }

    private Quaternion getAllPreviousRotation(int index) {
        Quaternion localSpaceRotation = ComponentsQuaternion.zero();
        for (int i = 0; i < index; i++) {
            Quaternion linkInverseRot = joints[i].rotation;
            localSpaceRotation.multiply(linkInverseRot);
        }
        return localSpaceRotation;
    }

    private float getLengthFromIndex(int index) {
        float length = 0;
        for (int i = 0; i < index; i++) {
            length += joints[i].getLength();
        }
        return length;
    }

    private int getEndJointIndex() {
        return joints.length-1;
    }

    public Vector3f getGoal() {
        return goal;
    }
}
