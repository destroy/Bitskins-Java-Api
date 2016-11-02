package spec45as.bitskins.error;


public class UnsuccessfulRequestException extends Exception {
    public UnsuccessfulRequestException() {
    }

    public UnsuccessfulRequestException(String message) {
        super(message);
    }

    public UnsuccessfulRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnsuccessfulRequestException(Throwable cause) {
        super(cause);
    }

    public UnsuccessfulRequestException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
