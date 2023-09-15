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
        for (int jointIndex = getEndJointIndex(); jointIndex >= 0; jointIndex--) {
            CCDJoint joint = joints[jointIndex];
            Quaternion fromToQuaternion = getRotationBetweenEndEffectorAndGoalUsingWorldSpace(jointIndex);
            joint.rotation.multiply(fromToQuaternion);
            joint.constrain();
            joint.limit();
        }
    }

    private Quaternion getRotationBetweenEndEffectorAndGoalUsingWorldSpace(int joinIndex) {
        Vector3f ei = lastEndEffectorWorldPosition==null ? endEffector.getWorldPosition() : lastEndEffectorWorldPosition;
        Vector3f et = goal;
        lastEndEffectorWorldPosition = null;

        Vector3f bonePos = getBonePos(joinIndex);
        Quaternion invLinkRot = getAllPreviousRotation(joinIndex+1);
        invLinkRot.inverse();

        Vector3f localEi = convertToBoneLocalSpace(ei, bonePos, invLinkRot);
        localEi.normalize();
        Vector3f localEt = convertToBoneLocalSpace(et, bonePos, invLinkRot);
        localEt.normalize();

        return ComponentsQuaternion.fromTwoVectors(localEi, localEt);
    }

    private static Vector3f convertToBoneLocalSpace(Vector3f worldPosition, Vector3f bonePos, Quaternion invLinkRot) {
        Vector3f localEi = worldPosition.duplicate().sub(bonePos);
        invLinkRot.transform(localEi);
        return localEi;
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

    private int getEndJointIndex() {
        return joints.length-1;
    }

    public Vector3f getGoal() {
        return goal;
    }
}
