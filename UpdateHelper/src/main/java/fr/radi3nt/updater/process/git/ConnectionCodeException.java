package fr.radi3nt.updater.process.git;

import java.io.IOException;

public class ConnectionCodeException extends IOException {

    private final int code;

    public ConnectionCodeException(int code) {
        this.code = code;
    }

    @Override
    public String getLocalizedMessage() {
        return "Invalid request: " + code;
    }
}
