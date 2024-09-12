package fr.radi3nt.gltf.importer;

import fr.radi3nt.gltf.data.GlTFResult;
import fr.radi3nt.gltf.data.scene.GlTFNode;
import fr.radi3nt.gltf.data.scene.GlTFTransform;
import fr.radi3nt.json.JsonArray;
import fr.radi3nt.json.JsonObject;
import fr.radi3nt.json.JsonValue;

public class NodesImporter implements GlTFImporter {

    public void parse(JsonObject file, GlTFResult result) {
        JsonArray nodesJsonArray = file.get("nodes").asArray();
        GlTFNode[] nodes = new GlTFNode[nodesJsonArray.size()];
        for (int i = 0; i < nodesJsonArray.size(); i++) {
            JsonObject nodeJson = nodesJsonArray.get(i).asObject();

            String name = nodeJson.getString("name", "undefined");
            int meshIndex = nodeJson.getInt("mesh", -1);
            int skinIndex = nodeJson.getInt("skin", -1);

            int[] children = getChildrenOrNull(nodeJson);
            GlTFTransform transform = GlTFTransform.parse(nodeJson);

            nodes[i] = new GlTFNode(name, children, transform, meshIndex, skinIndex);
        }

        result.nodes = nodes;
    }

    private static int[] getChildrenOrNull(JsonObject nodeJson) {
        JsonValue childrenJson = nodeJson.get("children");
        if (childrenJson == null) {
            return null;
        }
        JsonArray childrenJsonArray = childrenJson.asArray();
        int[] children = new int[childrenJsonArray.size()];
        for (int j = 0; j < childrenJsonArray.size(); j++) {
            children[j] = childrenJsonArray.get(j).asInt();
        }
        return children;
    }

}
