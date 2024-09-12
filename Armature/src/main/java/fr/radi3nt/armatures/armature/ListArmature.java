package fr.radi3nt.armatures.armature;

import fr.radi3nt.armatures.armature.bone.Bone;
import fr.radi3nt.maths.components.advanced.matrix.Matrix4x4;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collection;

public class ListArmature implements Armature {

    private final Collection<Bone> rootBones = new ArrayList<>();
    private final Matrix4x4 armatureTransform;
    private final Bone[] bonesIds;

    public ListArmature(Matrix4x4 armatureTransform, Bone[] bonesIds, Collection<Bone> roots) {
        this.armatureTransform = armatureTransform;
        this.bonesIds = bonesIds;
        this.rootBones.addAll(roots);
    }

    @Override
    public void encode(ByteBuffer byteBuffer) {
        for (Bone bonesId : bonesIds) {
            writeMatrix(byteBuffer, bonesId.getJointTransform());
        }
    }

    private void writeMatrix(ByteBuffer byteBuffer, Matrix4x4 worldSpaceTransform) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                byteBuffer.putFloat(worldSpaceTransform.get(i, j));
            }
        }
    }

    public void update() {
        for (Bone rootBone : rootBones) {
            rootBone.update(armatureTransform);
        }
    }

    public Matrix4x4 getArmatureTransform() {
        return armatureTransform;
    }

    @Override
    public Collection<Bone> getRoots() {
        return rootBones;
    }

    @Override
    public int getBoneCount() {
        return bonesIds.length;
    }
}
