package fr.radi3nt.gltf.data.mesh;

public class GlTFMesh {

    private final String name;
    private final Primitive[] primitives;

    public GlTFMesh(String name, Primitive[] primitives) {
        this.name = name;
        this.primitives = primitives;
    }

    public String getName() {
        return name;
    }

    public Primitive[] getPrimitives() {
        return primitives;
    }
}
