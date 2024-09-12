package fr.radi3nt.gltf.data.scene;

import fr.radi3nt.gltf.data.GlTFResult;

public class GlTFAbstractNode {

    private final String name;
    private final int[] children;

    public GlTFAbstractNode(String name, int[] children) {
        this.name = name;
        this.children = children;
    }

    public String getName() {
        return name;
    }

    public GlTFNode[] getChildren(GlTFResult result) {
        if (this.children==null)
            return new GlTFNode[0];

        GlTFNode[] children = new GlTFNode[this.children.length];
        for (int i = 0; i < children.length; i++) {
            children[i] = result.nodes[this.children[i]];
        }
        return children;
    }
}
