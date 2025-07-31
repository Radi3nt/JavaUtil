package fr.radi3nt.gltf;

import fr.radi3nt.file.impl.ResourceFile;
import fr.radi3nt.gltf.data.GlTFResult;
import fr.radi3nt.gltf.importer.*;
import fr.radi3nt.json.Json;
import fr.radi3nt.json.JsonValue;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;

public class GltfAllImporter {

    public GlTFResult importFile(String fullPath) throws IOException {
        int last = fullPath.lastIndexOf("/");
        String path = fullPath.substring(0, last);
        String name = fullPath.substring(last+1);
        return importFile(path, name);
    }

    public GlTFResult importFile(String parent, String fileName) throws IOException {
        GlTFResult result = new GlTFResult();


        Collection<GlTFImporter> importers = new ArrayList<>();
        {
            importers.add(new AccessorsImporter());
            importers.add(new MaterialsImporter());
            importers.add(new MeshesImporter());
            importers.add(new NodesImporter());
            importers.add(new ScenesImporter());
            importers.add(new SkinsImporter());
            importers.add(new ViewImporter());
        }
        importers.add(new BuffersImporter(parent));

        JsonValue parsed = Json.parse(new InputStreamReader(new ResourceFile(parent + "/" + fileName).getInputStream()));
        importers.parallelStream().forEach(importer -> importer.parse(parsed.asObject(), result));

        return result;
    }

}
