package fr.radi3nt.gltf.importer;

import fr.radi3nt.gltf.data.GlTFResult;
import fr.radi3nt.gltf.data.attributes.GlTFAttribute;
import fr.radi3nt.gltf.data.attributes.StringGLTFAttribute;
import fr.radi3nt.gltf.data.mesh.GlTFMesh;
import fr.radi3nt.gltf.data.mesh.Primitive;
import fr.radi3nt.json.JsonArray;
import fr.radi3nt.json.JsonObject;

import java.util.HashMap;
import java.util.Map;

public class MeshesImporter implements GlTFImporter {

    public void parse(JsonObject file, GlTFResult result) {
        JsonArray meshesJson = file.get("meshes").asArray();
        GlTFMesh[] meshes = new GlTFMesh[meshesJson.size()];
        for (int i = 0; i < meshesJson.size(); i++) {
            JsonObject meshJson = meshesJson.get(i).asObject();
            String name = meshJson.getString("name", "unnamed");

            JsonArray primitivesJson = meshJson.get("primitives").asArray();
            Primitive[] primitives = new Primitive[primitivesJson.size()];
            for (int j = 0; j < primitivesJson.size(); j++) {
                JsonObject primitiveJson = primitivesJson.get(j).asObject();
                JsonObject attributes = primitiveJson.get("attributes").asObject();
                Map<String, Integer> attributesMap = new HashMap<>();
                for (JsonObject.Member attribute : attributes) {
                    int accessorIndex = attribute.getValue().asInt();
                    attributesMap.put(attribute.getName(), accessorIndex);
                }

                int indicesIndex = primitiveJson.getInt("indices", -1);
                int material = primitiveJson.getInt("material", -1);

                primitives[j] = new Primitive(attributesMap, indicesIndex, material);
            }

            meshes[i] = new GlTFMesh(name, primitives);
        }

        result.meshes = meshes;
    }

}
