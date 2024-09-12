package fr.radi3nt.gltf.importer;

import fr.radi3nt.gltf.data.GlTFResult;
import fr.radi3nt.gltf.data.buffer.accessor.BufferAccessor;
import fr.radi3nt.gltf.data.buffer.accessor.GlTFAccessorType;
import fr.radi3nt.gltf.data.buffer.accessor.GlTFComponentType;
import fr.radi3nt.json.JsonArray;
import fr.radi3nt.json.JsonObject;
import fr.radi3nt.json.JsonValue;

public class AccessorsImporter implements GlTFImporter {

    public void parse(JsonObject file, GlTFResult result) {

        JsonArray accessorsJson = file.get("accessors").asArray();
        BufferAccessor[] accessors = new BufferAccessor[accessorsJson.size()];
        for (int i = 0; i < accessors.length; i++) {
            JsonObject accessorJson = accessorsJson.get(i).asObject();
            int bufferView = accessorJson.getInt("bufferView", -1);
            GlTFComponentType componentType = GlTFComponentType.fromId(accessorJson.getInt("componentType", -1));
            int count = accessorJson.getInt("count", 0);
            GlTFAccessorType accessorType = GlTFAccessorType.valueOf(accessorJson.getString("type"));

            float[] min = getMin(accessorJson);
            float[] max = getMax(accessorJson);

            accessors[i] = new BufferAccessor(bufferView, componentType, accessorType, count, min, max);
        }

        result.bufferAccessors = accessors;
    }

    private float[] getMin(JsonObject accessorJson) {
        JsonValue value = accessorJson.get("min");
        if (value==null)
            return null;

        return parseArray(value);
    }

    private float[] getMax(JsonObject accessorJson) {
        JsonValue value = accessorJson.get("max");
        if (value==null)
            return null;

        return parseArray(value);
    }

    private static float[] parseArray(JsonValue value) {
        JsonArray array = value.asArray();
        float[] floats = new float[array.size()];
        for (int i = 0; i < array.size(); i++) {
            floats[i] = array.get(i).asFloat();
        }
        return floats;
    }

}
