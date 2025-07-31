package fr.radi3nt.maths.dynamics;

import fr.radi3nt.maths.components.advanced.quaternions.Quaternion;
import fr.radi3nt.maths.components.vectors.Vector3f;

public interface SmoothTransform<P> {

    Vector3f getTargetPosition();
    Quaternion getTargetOrientation();

    Vector3f getCurrentPosition();
    Quaternion getCurrentOrientation();

    void setCurrentPosition(Vector3f position);
    void setCurrentOrientation(Quaternion orientation);

    void setTargetPosition(Vector3f position);
    void setTargetOrientation(Quaternion orientation);

    void setLinearProperties(P linear);
    void setAngularProperties(P angular);
    default void setAllProperties(P properties) {
        setLinearProperties(properties);
        setAngularProperties(properties);
    }

    void update(float delta);
}
