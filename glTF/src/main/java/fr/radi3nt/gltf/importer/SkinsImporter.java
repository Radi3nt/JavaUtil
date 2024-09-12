package fr.radi3nt.gltf.importer;

import fr.radi3nt.gltf.data.GlTFResult;
import fr.radi3nt.gltf.data.skins.GlTFSkin;
import fr.radi3nt.json.JsonArray;
import fr.radi3nt.json.JsonObject;
import fr.radi3nt.json.JsonValue;

public class SkinsImporter implements GlTFImporter {

    public void parse(JsonObject file, GlTFResult result) {

        JsonValue skinsValue = file.get("skins");
        if (skinsValue==null)
            return;
        JsonArray skinsJson = skinsValue.asArray();

        GlTFSkin[] skins = new GlTFSkin[skinsJson.size()];
        for (int i = 0; i < skins.length; i++) {
            JsonObject skinJson = skinsJson.get(i).asObject();

            String name = skinJson.getString("name", "unnamed");

            JsonArray jointsJson = skinJson.get("joints").asArray();
            int[] joints = new int[jointsJson.size()];
            for (int j = 0; j < joints.length; j++) {
                joints[j] = jointsJson.get(j).asInt();
            }

            int inverseBindMatrixAccessorIndex = skinJson.get("inverseBindMatrices").asInt();

            skins[i] = new GlTFSkin(name, inverseBindMatrixAccessorIndex, joints);
        }

        result.skins = skins;

    }

}
