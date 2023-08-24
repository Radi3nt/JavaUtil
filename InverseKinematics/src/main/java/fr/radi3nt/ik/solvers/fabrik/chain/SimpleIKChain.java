package fr.radi3nt.ik.solvers.fabrik.chain;

import fr.radi3nt.maths.components.advanced.quaternions.ComponentsQuaternion;
import fr.radi3nt.maths.components.advanced.quaternions.Quaternion;
import fr.radi3nt.maths.components.vectors.Vector3f;
import fr.radi3nt.maths.components.vectors.implementations.SimpleVector3f;

public class SimpleIKChain implements IKChain {

    private final IkLink[] ikLinks;
    private final float totalLength;

    public SimpleIKChain(IkLink... ikLinks) {
        this.ikLinks = ikLinks;
        float calculatingTotalLength = 0;
        for (IkLink ikLink : ikLinks) {
            calculatingTotalLength+= ikLink.getLength();
        }
        totalLength = calculatingTotalLength;
    }

    @Override
    public IkJointTransform setEndPointAndReturnCalculatedStart(IkJointTransform transform, int index) {
        IkLink currentLink = ikLinks[index];
        currentLink.setEndTransform(transform);

        calculateStartFromEnd(currentLink, index);

        return currentLink.getStartTransform();
    }

    private void calculateStartFromEnd(IkLink currentLink, int index) {
        Vector3f startPos = currentLink.getStartTransform().getResultPosition();
        Vector3f endPos = currentLink.getEndTransform().getResultPosition();
        Vector3f dirEndToStart = startPos.duplicate().sub(endPos);
        dirEndToStart.normalize();
        dirEndToStart.mul(currentLink.getLength());
        Quaternion quaternion = ComponentsQuaternion.fromTwoVectors(dirEndToStart, new SimpleVector3f(0, 0, 1));

        Quaternion localSpaceRotation = ComponentsQuaternion.zero();
        for (int i = 0; i < index; i++) {
            Quaternion linkInverseRot = ikLinks[i].getStartTransform().getResultRotation().duplicate();
            localSpaceRotation.multiply(linkInverseRot);
        }
        localSpaceRotation.inverse();

        localSpaceRotation.multiply(quaternion);

        currentLink.setStartTransform(new IkJointTransform(endPos.duplicate().sub(dirEndToStart), localSpaceRotation));
    }

    @Override
    public IkJointTransform setStartPointAndReturnCalculatedEnd(IkJointTransform transform, int index) {
        IkLink currentLink = ikLinks[index];
        currentLink.setStartTransform(transform);

        calculateEndFromStart(currentLink, index);

        return currentLink.getEndTransform();
    }

    private void calculateEndFromStart(IkLink currentLink, int index) {
        Vector3f startPos = currentLink.getStartTransform().getResultPosition();
        Vector3f endPos = currentLink.getEndTransform().getResultPosition();
        Vector3f dirStartToEnd = endPos.duplicate().sub(startPos);
        dirStartToEnd.normalize();

        Quaternion quaternion = ComponentsQuaternion.fromTwoVectors(new SimpleVector3f(0, 0, 1), dirStartToEnd);

        Quaternion localSpaceRotation = ComponentsQuaternion.zero();
        for (int i = 0; i < index; i++) {
            Quaternion linkInverseRot = ikLinks[i].getStartTransform().getResultRotation().duplicate();
            localSpaceRotation.multiply(linkInverseRot);
        }
        localSpaceRotation.inverse();

        localSpaceRotation.multiply(quaternion);

        currentLink.setEndTransform(new IkJointTransform(startPos.duplicate().sub(dirStartToEnd), ComponentsQuaternion.zero()));
        currentLink.setStartTransform(new IkJointTransform(currentLink.getStartTransform().getResultPosition(), localSpaceRotation));
    }

    @Override
    public IkJointTransform getEndPoint(int index) {
        return ikLinks[index].getEndTransform();
    }

    @Override
    public IkJointTransform getStartPoint(int index) {
        return ikLinks[index].getStartTransform();
    }

    public IkLink[] getIkLinks() {
        return ikLinks;
    }

    @Override
    public float getTotalLength() {
        return totalLength;
    }

    @Override
    public int getLinkAmount() {
        return ikLinks.length;
    }
}
