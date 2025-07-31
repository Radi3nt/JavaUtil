package fr.radi3nt.gltf.importer;

import fr.radi3nt.gltf.data.GlTFResult;
import fr.radi3nt.gltf.data.mesh.material.GlTFMaterial;
import fr.radi3nt.json.JsonArray;
import fr.radi3nt.json.JsonObject;

public class MaterialsImporter implements GlTFImporter {

    public void parse(JsonObject file, GlTFResult result) {
        if (file.get("materials")==null)
            return;

        JsonArray materialsJson = file.get("materials").asArray();
        GlTFMaterial[] materials = new GlTFMaterial[materialsJson.size()];
        for (int i = 0; i < materialsJson.size(); i++) {
            JsonObject materialJson = materialsJson.get(i).asObject();
            String name = materialJson.getString("name", "unnamed");
            materials[i] = new GlTFMaterial(name);
        }

        result.materials = materials;
    }

}
