package fr.radi3nt.armatures.armature.bone;

import fr.radi3nt.armatures.armature.driver.BoneDriver;
import fr.radi3nt.armatures.armature.driver.BoneRestData;
import fr.radi3nt.armatures.armature.driver.ParentedBoneDriver;
import fr.radi3nt.maths.components.advanced.matrix.ArrayMatrix4x4;
import fr.radi3nt.maths.components.advanced.matrix.Matrix4x4;

import java.util.ArrayList;
import java.util.Collection;

public class ParentBone implements Bone {

    private final Matrix4x4 jointSpaceTransform = ArrayMatrix4x4.newIdentity();
    private final Matrix4x4 modelSpaceTransform = ArrayMatrix4x4.newIdentity();

    private final Matrix4x4 worldInverseBindTransform;
    private final BoneRestData boneRestData;

    private BoneDriver boneDriver;

    public final Collection<Bone> children = new ArrayList<>();

    public ParentBone(Matrix4x4 worldInverseBindTransform, BoneRestData boneRestData, Collection<Bone> children) {
        this.worldInverseBindTransform = worldInverseBindTransform;
        this.boneRestData = boneRestData;

        this.children.addAll(children);

        boneDriver = new ParentedBoneDriver(boneRestData);
    }

    @Override
    public void update(Matrix4x4 parentModelSpace) {
        modelSpaceTransform.copy(boneDriver.getModelSpaceTransform(parentModelSpace));

        for (Bone child : children) {
            child.update(modelSpaceTransform);
        }

        jointSpaceTransform.copy(modelSpaceTransform);
        jointSpaceTransform.multiply(worldInverseBindTransform);
    }

    @Override
    public void setBoneDriver(BoneDriver boneDriver) {
        this.boneDriver = boneDriver;
    }

    public Collection<Bone> getChildren() {
        return children;
    }

    @Override
    public BoneRestData getBoneRestData() {
        return boneRestData;
    }

    @Override
    public BoneDriver getBoneDriver() {
        return boneDriver;
    }

    @Override
    public Matrix4x4 getWorldTransform() {
        return modelSpaceTransform;
    }

    @Override
    public Matrix4x4 getJointTransform() {
        return jointSpaceTransform;
    }

}
