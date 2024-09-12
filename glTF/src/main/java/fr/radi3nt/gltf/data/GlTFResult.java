package fr.radi3nt.gltf.data;

import fr.radi3nt.gltf.data.buffer.GlTFBuffer;
import fr.radi3nt.gltf.data.buffer.accessor.BufferAccessor;
import fr.radi3nt.gltf.data.buffer.view.BufferView;
import fr.radi3nt.gltf.data.mesh.GlTFMesh;
import fr.radi3nt.gltf.data.mesh.material.GlTFMaterial;
import fr.radi3nt.gltf.data.scene.GlTFNode;
import fr.radi3nt.gltf.data.scene.GlTFScene;
import fr.radi3nt.gltf.data.skins.GlTFSkin;

import java.util.Arrays;

public class GlTFResult {

    public GlTFScene[] scenes;
    public GlTFNode[] nodes;

    public GlTFMesh[] meshes;
    public GlTFSkin[] skins;
    public GlTFMaterial[] materials;

    public BufferAccessor[] bufferAccessors;
    public BufferView[] bufferViews;
    public GlTFBuffer[] buffers;

    @Override
    public String toString() {
        return "GlTFResult{" +
                "scenes=" + Arrays.toString(scenes) +
                ", nodes=" + Arrays.toString(nodes) +
                ", meshes=" + Arrays.toString(meshes) +
                ", skins=" + Arrays.toString(skins) +
                ", materials=" + Arrays.toString(materials) +
                ", bufferAccessors=" + Arrays.toString(bufferAccessors) +
                ", bufferViews=" + Arrays.toString(bufferViews) +
                ", buffers=" + Arrays.toString(buffers) +
                '}';
    }

    public GlTFNode getNodeByName(String name) {
        for (GlTFNode node : nodes) {
            if (node.getName().equals(name)) {
                return node;
            }
        }
        return null;
    }
}
