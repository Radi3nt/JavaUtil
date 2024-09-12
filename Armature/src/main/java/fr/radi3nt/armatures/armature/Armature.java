package fr.radi3nt.armatures.armature;

import fr.radi3nt.armatures.armature.bone.Bone;

import java.nio.ByteBuffer;
import java.util.Collection;

public interface Armature {

    void encode(ByteBuffer byteBuffer);

    Collection<Bone> getRoots();
    int getBoneCount();

}
