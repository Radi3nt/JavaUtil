package fr.radi3nt.gltf.data.mesh.material;

import fr.radi3nt.gltf.data.GlTFResult;

public interface GlTFMaterialIndexer {

    int getIndex(GlTFMaterial material, GlTFResult result);

}
