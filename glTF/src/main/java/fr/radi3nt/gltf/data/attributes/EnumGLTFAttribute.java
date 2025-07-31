package fr.radi3nt.gltf.data.attributes;

public enum EnumGLTFAttribute implements GlTFAttribute {

    POSITION,
    NORMAL,
    JOINTS_0,
    JOINTS_1,
    WEIGHTS_0,
    WEIGHTS_1

    ;

    @Override
    public String getId() {
        return name();
    }
}
