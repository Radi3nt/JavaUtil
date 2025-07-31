package fr.radi3nt.gltf.data.attributes;

public class StringGLTFAttribute implements GlTFAttribute {

    private final String id;

    public StringGLTFAttribute(String id) {
        this.id = id;
    }

    @Override
    public String getId() {
        return id;
    }
}
