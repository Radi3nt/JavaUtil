package fr.radi3nt.animations.importing.anim.content;

import fr.radi3nt.file.files.ReadableFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ReadableAnimFileContent implements AnimFileContent {

    private final ReadableFile readableFile;
    private List<String> lines = new ArrayList<>();
    private int lineIndex = 0;

    public ReadableAnimFileContent(ReadableFile readableFile) {
        this.readableFile = readableFile;
    }

    public void readAll() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(readableFile.getInputStream()))) {
            lines = reader.lines().collect(Collectors.toList());
            lineIndex = 0;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String peakLine() {
        return formatLine(lines.get(lineIndex));
    }

    @Override
    public String readLine() {
        return formatLine(lines.get(lineIndex++));
    }

    @Override
    public void nextLine() {
        lineIndex++;
    }

    @Override
    public boolean hasLine() {
        return lineIndex<lines.size();
    }

    private String formatLine(String s) {
        String formatted = s.trim();
        int index = formatted.lastIndexOf(";");
        if (index!=-1)
            formatted = formatted.substring(0, index);
        return formatted;
    }

}
