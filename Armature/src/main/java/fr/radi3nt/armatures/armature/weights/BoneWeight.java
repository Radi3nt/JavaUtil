package fr.radi3nt.armatures.armature.weights;

public class BoneWeight {

    private final int bone;
    private float weight;

    public BoneWeight(int bone, float weight) {
        this.bone = bone;
        this.weight = weight;
    }

    public int getBoneIndex() {
        return bone;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }
}
