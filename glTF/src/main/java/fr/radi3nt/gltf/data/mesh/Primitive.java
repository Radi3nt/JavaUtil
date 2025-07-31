package fr.radi3nt.gltf.data.mesh;

import fr.radi3nt.gltf.data.GlTFResult;
import fr.radi3nt.gltf.data.attributes.GlTFAttribute;
import fr.radi3nt.gltf.data.buffer.accessor.BufferAccessor;
import fr.radi3nt.gltf.data.mesh.material.GlTFMaterial;

import java.nio.ByteBuffer;
import java.util.Map;

public class Primitive {

    private final Map<String, Integer> attributesToAccessors;
    private final int indicesAccessor;
    private final int materialSlot;

    public Primitive(Map<String, Integer> attributesToAccessors, int indicesAccessor, int materialSlot) {
        this.attributesToAccessors = attributesToAccessors;
        this.indicesAccessor = indicesAccessor;
        this.materialSlot = materialSlot;
    }

    public ByteBuffer get(GlTFAttribute attributes, GlTFResult result) {
        BufferAccessor bufferAccessor = getAccessor(attributes, result);
        return bufferAccessor.getBuffer(result);
    }

    public void get(GlTFAttribute attributes, GlTFResult result, ByteBuffer buffer) {
        getAccessor(attributes, result).getBuffer(result, buffer);
    }

    public int getByteSize(GlTFAttribute attribute, GlTFResult result) {
        BufferAccessor bufferAccessor = getAccessor(attribute, result);
        return bufferAccessor.getByteSize();
    }

    public int getComponentByte(GlTFAttribute attribute, GlTFResult result) {
        BufferAccessor bufferAccessor = getAccessor(attribute, result);
        return bufferAccessor.getComponentBytes();
    }

    public int getComponentCount(GlTFAttribute attribute, GlTFResult result) {
        BufferAccessor bufferAccessor = getAccessor(attribute, result);
        return bufferAccessor.getComponentCount();
    }

    public int getCount(GlTFAttribute attribute, GlTFResult result) {
        BufferAccessor bufferAccessor = getAccessor(attribute, result);
        return bufferAccessor.getCount();
    }

    private BufferAccessor getAccessor(GlTFAttribute attribute, GlTFResult result) {
        int accessor = attributesToAccessors.get(attribute.getId());
        return result.bufferAccessors[accessor];
    }

    public ByteBuffer getIndices(GlTFResult result) {
        return result.bufferAccessors[indicesAccessor].getBuffer(result);
    }

    public int getCount(GlTFResult result) {
        return result.bufferAccessors[indicesAccessor].getCount();
    }

    public GlTFMaterial getMaterial(GlTFResult result) {
        if (materialSlot==-1)
            return null;
        return result.materials[materialSlot];
    }

}
