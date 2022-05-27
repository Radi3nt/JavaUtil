package fr.radi3nt.updater.download;

import fr.radi3nt.file.files.WritableFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class URLDownloader extends InputStreamDownloader {

    private final URL url;

    public URLDownloader(URL url, WritableFile fileOutput) {
        super(fileOutput);
        this.url = url;
    }

    @Override
    protected InputStream createInputStream() {
        try {
            return url.openConnection().getInputStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
