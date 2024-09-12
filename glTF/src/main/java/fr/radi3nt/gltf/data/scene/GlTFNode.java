package fr.radi3nt.gltf.data.scene;

import fr.radi3nt.gltf.data.GlTFResult;
import fr.radi3nt.gltf.data.mesh.GlTFMesh;
import fr.radi3nt.gltf.data.skins.GlTFSkin;

public class GlTFNode extends GlTFAbstractNode {

    private final GlTFTransform localTransform;

    private final int meshIndex;
    private final int skinIndex;

    public GlTFNode(String name, int[] children, GlTFTransform localTransform, int meshIndex, int skinIndex) {
        super(name, children);
        this.localTransform = localTransform;
        this.meshIndex = meshIndex;
        this.skinIndex = skinIndex;
    }

    public GlTFTransform getLocalTransform() {
        return localTransform;
    }

    public GlTFMesh getMesh(GlTFResult result) {
        if (meshIndex==-1)
            return null;
        return result.meshes[meshIndex];
    }

    public int getMeshIndex() {
        return meshIndex;
    }

    public GlTFSkin getSkin(GlTFResult result) {
        return result.skins[skinIndex];
    }

}
