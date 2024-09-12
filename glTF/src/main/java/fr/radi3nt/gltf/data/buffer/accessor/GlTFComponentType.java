package fr.radi3nt.gltf.data.buffer.accessor;

public enum GlTFComponentType {

    GL_FLOAT(5126, 4),
    GL_UNSIGNED_BYTE(5121, 1),
    GL_UNSIGNED_SHORT(5123, 2),

    ;

    private final int id;
    private final int bytes;

    GlTFComponentType(int id, int bytes) {
        this.id = id;
        this.bytes = bytes;
    }

    public static GlTFComponentType fromId(int componentType) {
        for (GlTFComponentType value : values()) {
            if (value.id==componentType) return value;
        }
        return null;
    }

    public int bytes() {
        return bytes;
    }
}
