package fr.radi3nt.gltf.importer;

import fr.radi3nt.gltf.data.GlTFResult;
import fr.radi3nt.gltf.data.buffer.view.BufferView;
import fr.radi3nt.json.JsonArray;
import fr.radi3nt.json.JsonObject;

public class ViewImporter implements GlTFImporter {

    public void parse(JsonObject file, GlTFResult result) {

        JsonArray bufferViewsJson = file.get("bufferViews").asArray();
        BufferView[] bufferViews = new BufferView[bufferViewsJson.size()];
        for (int i = 0; i < bufferViewsJson.size(); i++) {
            JsonObject viewJson = bufferViewsJson.get(i).asObject();
            int buffer = viewJson.getInt("buffer", -1);
            int byteLength = viewJson.getInt("byteLength", 0);
            long byteOffset = viewJson.getLong("byteOffset", 0);
            bufferViews[i] = new BufferView(buffer, byteLength, byteOffset);
        }

        result.bufferViews = bufferViews;

    }

}
