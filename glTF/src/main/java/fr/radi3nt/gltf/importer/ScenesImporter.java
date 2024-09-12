package fr.radi3nt.gltf.importer;

import fr.radi3nt.gltf.data.GlTFResult;
import fr.radi3nt.gltf.data.scene.GlTFScene;
import fr.radi3nt.json.JsonArray;
import fr.radi3nt.json.JsonObject;

public class ScenesImporter implements GlTFImporter {

    public void parse(JsonObject file, GlTFResult result) {
        JsonArray scenesJson = file.get("scenes").asArray();
        GlTFScene[] scenes = new GlTFScene[scenesJson.size()];
        for (int i = 0; i < scenesJson.size(); i++) {
            JsonObject sceneJson = scenesJson.get(i).asObject();
            String sceneName = sceneJson.asObject().getString("name");
            JsonArray nodesJson = sceneJson.asObject().get("nodes").asArray();
            int[] nodes = new int[nodesJson.size()];
            for (int j = 0; j < nodesJson.size(); j++) {
                nodes[j] = nodesJson.get(j).asInt();
            }

            GlTFScene currentScene = new GlTFScene(sceneName, nodes);
            scenes[i] = currentScene;
        }

        result.scenes = scenes;
    }

}
