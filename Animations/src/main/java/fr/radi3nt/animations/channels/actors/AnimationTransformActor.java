package fr.radi3nt.animations.channels.actors;

import fr.radi3nt.maths.components.advanced.matrix.Matrix4x4;
import fr.radi3nt.maths.components.advanced.quaternions.Quaternion;
import fr.radi3nt.maths.components.vectors.Vector3f;

public interface AnimationTransformActor extends AnimationActor {

    void setTranslation(Vector3f translation);
    void setRotation(Quaternion rotation);
    void setScale(Vector3f scale);

    Matrix4x4 getTransform();
    Vector3f getTranslation();
    Quaternion getRotation();
    Vector3f getScale();

}
