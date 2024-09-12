package fr.radi3nt.gltf.importer;

import fr.radi3nt.file.files.ReadableFile;
import fr.radi3nt.file.impl.ResourceFile;
import fr.radi3nt.gltf.data.GlTFResult;
import fr.radi3nt.gltf.data.buffer.GlTFBuffer;
import fr.radi3nt.json.JsonArray;
import fr.radi3nt.json.JsonObject;

public class BuffersImporter implements GlTFImporter {

    private final String basePath;

    public BuffersImporter(String basePath) {
        this.basePath = basePath;
    }

    public void parse(JsonObject file, GlTFResult result) {

        JsonArray buffersJson = file.get("buffers").asArray();
        GlTFBuffer[] buffers = new GlTFBuffer[buffersJson.size()];
        for (int i = 0; i < buffersJson.size(); i++) {
            JsonObject bufferJson = buffersJson.get(i).asObject();
            long byteLength = bufferJson.getLong("byteLength", 0);
            ReadableFile uri = new ResourceFile(basePath + "/" + bufferJson.getString("uri"));
            buffers[i] = new GlTFBuffer(uri, byteLength);
        }

        result.buffers = buffers;

    }

}
