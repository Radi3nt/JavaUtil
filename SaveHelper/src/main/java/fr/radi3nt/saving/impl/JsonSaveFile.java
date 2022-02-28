package fr.radi3nt.saving.impl;

import fr.radi3nt.file.files.CompleteFile;
import fr.radi3nt.json.Json;
import fr.radi3nt.json.JsonObject;
import fr.radi3nt.json.JsonValue;
import fr.radi3nt.json.WriterConfig;
import fr.radi3nt.saving.SaveFile;
import fr.radi3nt.saving.exceptions.FileOperationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

public class JsonSaveFile implements SaveFile {

    private final CompleteFile file;
    private JsonValue jsonObject;
    private boolean upToDate = true;

    public JsonSaveFile(CompleteFile file) {
        this.file = file;
    }


    @Override
    public void save() throws FileOperationException {
        try {
            saveJson();
        } catch (Exception e) {
            throw new FileOperationException(e);
        }
    }

    @Override
    public void load() throws FileOperationException {
        try {
            loadJson();
        } catch (Exception e) {
            throw new FileOperationException(e);
        }
    }

    @Override
    public boolean isLoaded() {
        return jsonObject!=null;
    }

    @Override
    public boolean isFileUpToDate() {
        return upToDate;
    }

    private void loadJson() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(file.getInputStream()));
        jsonObject = Json.parse(bufferedReader);
        bufferedReader.close();
    }

    private void saveJson() throws IOException {
        if (!file.isCreated()) {
            file.create();
        }
        final OutputStreamWriter fw = new OutputStreamWriter(file.getOutputSteam(), StandardCharsets.UTF_8);
        jsonObject.writeTo(fw, WriterConfig.PRETTY_PRINT);
        fw.close();

        upToDate = true;
    }

    public JsonValue getJsonObject() {
        return jsonObject;
    }

    public void setJsonObject(JsonObject jsonObject) {
        this.jsonObject = jsonObject;
        upToDate = false;
    }
}
