package fr.radi3nt.gltf.data.buffer.accessor;

public enum GlTFAccessorType {

    SCALAR(1),
    VEC3(3),
    VEC4(4),
    MAT4(16);

    private final int amount;

    GlTFAccessorType(int amount) {
        this.amount = amount;
    }

    public int amount() {
        return amount;
    }
}
