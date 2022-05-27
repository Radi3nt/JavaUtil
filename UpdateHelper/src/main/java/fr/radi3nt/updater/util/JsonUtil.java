package fr.radi3nt.updater.util;

import fr.radi3nt.file.impl.NioFile;
import fr.radi3nt.json.Json;
import fr.radi3nt.json.JsonValue;

import java.io.IOException;
import java.io.InputStreamReader;

public class JsonUtil {

    public static JsonValue getJsonValue(NioFile config) {
        JsonValue configJson;
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(config.getInputStream());
            configJson = Json.parse(inputStreamReader);
            inputStreamReader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return configJson;
    }

}
