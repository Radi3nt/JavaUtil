package fr.radi3nt.gltf.data.skins;

import fr.radi3nt.gltf.data.GlTFResult;
import fr.radi3nt.gltf.data.scene.GlTFNode;
import fr.radi3nt.maths.components.advanced.matrix.ArrayMatrix4x4;
import fr.radi3nt.maths.components.advanced.matrix.Matrix4x4;

import java.nio.ByteBuffer;

public class GlTFSkin {

    private final String name;
    private final int inverseBindMatricesAccessorIndex;
    private final int[] joints;

    public GlTFSkin(String name, int inverseBindMatricesAccessorIndex, int[] joints) {
        this.name = name;
        this.inverseBindMatricesAccessorIndex = inverseBindMatricesAccessorIndex;
        this.joints = joints;
    }

    public String getName() {
        return name;
    }

    public GlTFNode[] getJoints(GlTFResult result) {
        GlTFNode[] nodes = new GlTFNode[joints.length];
        for (int i = 0; i < joints.length; i++) {
            nodes[i] = result.nodes[joints[i]];
        }
        return nodes;
    }

    public Matrix4x4[] getInverseBindMatrices(GlTFResult result) {
        ByteBuffer buffer = result.bufferAccessors[inverseBindMatricesAccessorIndex].getBuffer(result);
        Matrix4x4[] matrices = new Matrix4x4[joints.length];
        for (int jointIndex = 0; jointIndex < joints.length; jointIndex++) {
            float[] mat = new float[4*4];
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    float value = buffer.getFloat();
                    mat[i+j*4] = value;
                }
            }
            Matrix4x4 inverseBind = ArrayMatrix4x4.from(mat);
            matrices[jointIndex] = inverseBind;
        }
        return matrices;
    }
}
