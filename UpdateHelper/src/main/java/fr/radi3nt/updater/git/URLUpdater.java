package fr.radi3nt.updater.git;

import fr.radi3nt.file.files.WritableFile;
import fr.radi3nt.updater.ReadableByteChannelUpdater;
import fr.radi3nt.updater.update.URLUpdate;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public class URLUpdater extends ReadableByteChannelUpdater {

    private final URLUpdate update;

    public URLUpdater(URLUpdate update, WritableFile fileOutput) {
        super(fileOutput);
        this.update = update;
    }

    @Override
    protected ReadableByteChannel createReadableChannel() {
        URL url;
        try {
            url = update.createURL();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

        try {
            return Channels.newChannel(url.openConnection().getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
