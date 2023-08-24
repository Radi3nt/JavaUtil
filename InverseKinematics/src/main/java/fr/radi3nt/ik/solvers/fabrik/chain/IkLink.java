package fr.radi3nt.ik.solvers.fabrik.chain;

import fr.radi3nt.maths.components.vectors.Vector3f;

public class IkLink {

    private final float length;

    private IkJointTransform startTransform;
    private IkJointTransform endTransform;

    public IkLink(float length, IkJointTransform startTransform, IkJointTransform endTransform) {
        this.length = length;
        this.startTransform = startTransform;
        this.endTransform = endTransform;
    }

    public float getLength() {
        return length;
    }

    public IkJointTransform getStartTransform() {
        return startTransform;
    }

    public void setStartTransform(IkJointTransform startTransform) {
        this.startTransform = startTransform;
    }

    public IkJointTransform getEndTransform() {
        return endTransform;
    }

    public void setEndTransform(IkJointTransform endTransform) {
        this.endTransform = endTransform;
    }

    public Vector3f getDirection() {
        return endTransform.getResultPosition().duplicate().sub(startTransform.getResultPosition()).normalize();
    }
}