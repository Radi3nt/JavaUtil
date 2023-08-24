package fr.radi3nt.ik.solvers.ccdik.effectors;

import fr.radi3nt.ik.solvers.ccdik.CCDJoint;
import fr.radi3nt.maths.components.advanced.quaternions.ComponentsQuaternion;
import fr.radi3nt.maths.components.advanced.quaternions.Quaternion;
import fr.radi3nt.maths.components.vectors.Vector3f;
import fr.radi3nt.maths.components.vectors.implementations.SimpleVector3f;

public class JointsEffector implements Effector {

    private final Vector3f startPos;
    private final Vector3f upAxis;

    private final CCDJoint[] joints;
    private final float totalLength;

    public JointsEffector(Vector3f startPos, Vector3f upAxis, CCDJoint... joints) {
        this.startPos = startPos;
        this.joints = joints;
        this.upAxis = upAxis;
        float totalLengthChain = 0;
        for (CCDJoint joint : joints) {
            totalLengthChain+=joint.getLength();
        }
        totalLength = totalLengthChain;
    }

    @Override
    public Vector3f getWorldPosition() {
        return getBonePos().add(startPos);
    }

    private Vector3f getBonePos() {
        Quaternion cumulatedRotation = ComponentsQuaternion.zero();
        Vector3f worldPos = new SimpleVector3f();
        for (CCDJoint joint : joints) {
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

}
