package fr.radi3nt.maths.dynamics;

import fr.radi3nt.maths.components.vectors.Vector4f;
import fr.radi3nt.maths.components.vectors.implementations.SimpleVector4f;

public class VectorRotationSmoothDynamics extends VectorSmoothDynamics<Vector4f> {

    private final boolean loop;

    public VectorRotationSmoothDynamics(DynamicsConstants constants, Vector4f start) {
        this(constants, start, true);
    }

    public VectorRotationSmoothDynamics(DynamicsConstants constants, Vector4f start, boolean loop) {
        super(constants, start);
        this.loop = loop;
    }

    @Override
    public void update(float step) {
        if (loop && inputPrevious.dot(inputCurrent)<0) {
            inputCurrent.mul(-1);
        }

        super.update(step);
        response.normalize();
    }

    public void setCurrentBypass(SimpleVector4f current) {
        this.response = current;
    }
}
