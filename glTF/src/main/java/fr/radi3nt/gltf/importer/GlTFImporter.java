package fr.radi3nt.gltf.importer;

import fr.radi3nt.gltf.data.GlTFResult;
import fr.radi3nt.json.JsonObject;

public interface GlTFImporter {

    void parse(JsonObject file, GlTFResult result);

}
