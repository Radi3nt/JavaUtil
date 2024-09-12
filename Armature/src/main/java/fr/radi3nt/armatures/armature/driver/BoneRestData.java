package fr.radi3nt.armatures.armature.driver;

import fr.radi3nt.maths.components.advanced.matrix.ArrayMatrix4x4;
import fr.radi3nt.maths.components.advanced.matrix.Matrix4x4;
import fr.radi3nt.maths.components.advanced.quaternions.Quaternion;
import fr.radi3nt.maths.components.vectors.Vector3f;

public class BoneRestData {

    private final Vector3f position;
    private final Quaternion rotation;
    private final Vector3f scale;

    public BoneRestData(Vector3f position, Quaternion rotation, Vector3f scale) {
        this.position = position;
        this.rotation = rotation;
        this.scale = scale;
    }

    public Matrix4x4 toMatrix() {
        Matrix4x4 result = ArrayMatrix4x4.newIdentity();
        result.quaternionRotation(rotation);
        result.translation(position);
        result.scalar(scale);
        return result;
    }

    public Vector3f getPosition() {
        return position;
    }

    public Quaternion getRotation() {
        return rotation;
    }

    public Vector3f getScale() {
        return scale;
    }
}
