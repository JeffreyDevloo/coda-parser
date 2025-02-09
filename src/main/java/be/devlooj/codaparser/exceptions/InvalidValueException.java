package be.devlooj.codaparser.exceptions;

public class InvalidValueException extends RuntimeException {
    public InvalidValueException(String type, Object value, String errorMessage) {
        super("Value \"" + value + "\" is invalid for " + type + " (" + errorMessage + ")");
    }
}