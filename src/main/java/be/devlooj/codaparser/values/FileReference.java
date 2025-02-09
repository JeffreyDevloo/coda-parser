package be.devlooj.codaparser.values;

import be.devlooj.codaparser.Helpers;

public class FileReference {
    private final String value;

    public FileReference(String value) {
        Helpers.validateStringLength(value, 10, "FileReference");
        this.value = value.trim();
    }

    public String getValue() {
        return value;
    }
}
