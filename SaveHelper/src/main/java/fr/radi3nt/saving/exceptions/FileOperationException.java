package fr.radi3nt.saving.exceptions;

public class FileOperationException extends Exception {

    public FileOperationException() {
    }

    public FileOperationException(String message) {
        super(message);
    }

    public FileOperationException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileOperationException(Throwable cause) {
        super(cause);
    }

    public FileOperationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
