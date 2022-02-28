package fr.radi3nt.lang.util;

import fr.radi3nt.json.Json;
import fr.radi3nt.json.JsonObject;
import fr.radi3nt.json.JsonValue;
import fr.radi3nt.lang.creator.LanguageCreator;
import fr.radi3nt.lang.languages.LanguageIndexer;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.*;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public final class LangFileUtil { //todo do it all again

    public static boolean loadAllLanguage(File folder, LanguageIndexer languageIndexer) {
        File[] filesList = getLangFiles(folder);
        if (filesList != null) {
            for (File file : filesList) {
                if (!file.isHidden() && file.getName().contains(".json")) {
                    LanguageCreator.setup(file, languageIndexer);
                } else {
                    System.out.println("[Language] Skipping loading of: " + file.getName());
                }
            }
        }
        return true;
    }

    public static void copyPath(Path toCopy, Path toPaste) {
        try {
            Files.copy(toCopy, toPaste, StandardCopyOption.REPLACE_EXISTING);
            if (Files.isDirectory(toCopy)) {
                for (Path path2 : Files.list(toCopy).collect(Collectors.toList())) {
                    copyPath(path2, toPaste.resolve(path2.getFileName().toString()));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void copyLocalToLang(Class<?> mainClass, File lngFile) {
        URI uri = null;
        try {
            uri = mainClass.getResource("/langs").toURI();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        Map<String, String> env = new HashMap<>();
        try (FileSystem zipfs = FileSystems.newFileSystem(uri, env)) {
            for (Path path : zipfs.getRootDirectories()) {
                for (Path path1 : Files.list(path.resolve("/langs")).collect(Collectors.toList())) {
                    File directoryPath = new File(lngFile + "/langs/" + path1.getFileName().toString());
                    if (!directoryPath.exists()) {
                        copyPath(path1, directoryPath.toPath());
                    } else
                        System.out.println("[Language] Skipping copying of: '" + directoryPath.getName() + "' because it's already there");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static File[] getLangFiles(File folder) {
        File directoryPath = new File(folder + "/langs");
        return directoryPath.listFiles();
    }


    public static JsonObject loadTranslations(final String file) {
        return Json.parse(file).asObject();
    }

    public static Map<String, String> loadTranslations(final JsonObject jsonObject) {
        return loadTranslationsRec("", jsonObject, new HashMap<>());
    }

    private static Map<String, String> loadTranslationsRec(final String currentPath, final JsonValue jsonValue, final Map<String, String> keys) {
        if (jsonValue.isObject()) {
            for (final JsonObject.Member member : jsonValue.asObject()) {
                final String newPath = String.format("%s%s%s", currentPath, currentPath.equals("") ? "" : ".", member.getName());
                loadTranslationsRec(newPath, member.getValue(), keys);
            }
        } else if (!jsonValue.isNull()) {
            keys.put(currentPath, jsonValue.asString());
        }
        return keys;
    }

}
