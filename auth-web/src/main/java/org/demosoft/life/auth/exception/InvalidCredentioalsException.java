package org.demosoft.life.auth.exception;

public class InvalidCredentioalsException extends RuntimeException {
    public InvalidCredentioalsException() {
    }

    public InvalidCredentioalsException(String message) {
        super(message);
    }

    public InvalidCredentioalsException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidCredentioalsException(Throwable cause) {
        super(cause);
    }

    public InvalidCredentioalsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
