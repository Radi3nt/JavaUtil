package fr.radi3nt.armatures.armature.bone;

import fr.radi3nt.armatures.armature.driver.BoneDriver;
import fr.radi3nt.armatures.armature.driver.BoneRestData;
import fr.radi3nt.maths.components.advanced.matrix.Matrix4x4;

public interface Bone {

    void update(Matrix4x4 parentModelSpace);

    void setBoneDriver(BoneDriver boneDriver);

    BoneRestData getBoneRestData();

    BoneDriver getBoneDriver();

    Matrix4x4 getWorldTransform();
    Matrix4x4 getJointTransform();

}
