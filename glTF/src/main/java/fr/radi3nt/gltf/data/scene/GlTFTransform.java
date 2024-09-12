package fr.radi3nt.gltf.data.scene;

import fr.radi3nt.json.JsonArray;
import fr.radi3nt.json.JsonObject;
import fr.radi3nt.json.JsonValue;
import fr.radi3nt.maths.components.advanced.matrix.ArrayMatrix4x4;
import fr.radi3nt.maths.components.advanced.matrix.Matrix4x4;
import fr.radi3nt.maths.components.advanced.quaternions.ComponentsQuaternion;
import fr.radi3nt.maths.components.advanced.quaternions.Quaternion;
import fr.radi3nt.maths.components.vectors.Vector3f;
import fr.radi3nt.maths.components.vectors.implementations.SimpleVector3f;

import java.util.function.Supplier;

public class GlTFTransform {

    private final Vector3f translation;
    private final Quaternion rotation;
    private final Vector3f scale;

    public GlTFTransform(Vector3f translation, Quaternion rotation, Vector3f scale) {
        this.translation = translation;
        this.rotation = rotation;
        this.scale = scale;
    }

    public Matrix4x4 toMatrix() {
        Matrix4x4 result = ArrayMatrix4x4.newIdentity();
        result.quaternionRotation(rotation);
        result.translation(translation);
        result.scalar(scale);
        return result;
    }

    public Vector3f transform(Vector3f original) {
        Vector3f result = original.duplicate();
        result.mul(scale);
        rotation.transform(result);
        result.add(translation);
        return result;
    }

    public Vector3f transformOrientation(Vector3f original) {
        Vector3f result = original.duplicate();
        rotation.transform(result);
        return result;
    }

    public Vector3f getTranslation() {
        return translation;
    }

    public Quaternion getRotation() {
        return rotation;
    }

    public Vector3f getScale() {
        return scale;
    }

    public static GlTFTransform parse(JsonObject nodeJson) {
        Vector3f translation = parseTranslation(nodeJson);
        Quaternion rotation = parseRotation(nodeJson);
        Vector3f scale = parseScale(nodeJson);

        return new GlTFTransform(translation, rotation, scale);
    }

    private static Vector3f parseScale(JsonObject nodeJson) {
        JsonValue value = nodeJson.get("scale");
        return parseVector(value, () -> new SimpleVector3f(1f, 1f, 1f));
    }

    private static Quaternion parseRotation(JsonObject nodeJson) {
        JsonValue value = nodeJson.get("rotation");
        if (value == null) {
            return ComponentsQuaternion.zero();
        }

        JsonArray valueArray = value.asArray();
        return new ComponentsQuaternion(valueArray.get(0).asFloat(), valueArray.get(1).asFloat(), valueArray.get(2).asFloat(), valueArray.get(3).asFloat());
    }

    private static Vector3f parseTranslation(JsonObject nodeJson) {
        JsonValue value = nodeJson.get("translation");
        return parseVector(value, SimpleVector3f::new);
    }

    private static Vector3f parseVector(JsonValue value, Supplier<Vector3f> defaultVec) {
        if (value == null) {
            return defaultVec.get();
        }

        JsonArray valueArray = value.asArray();
        return new SimpleVector3f(valueArray.get(0).asFloat(), valueArray.get(1).asFloat(), valueArray.get(2).asFloat());
    }
}
