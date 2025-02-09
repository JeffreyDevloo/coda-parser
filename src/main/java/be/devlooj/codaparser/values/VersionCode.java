package be.devlooj.codaparser.values;

import be.devlooj.codaparser.Helpers;

public class VersionCode {
    private final String value;

    public VersionCode(String value) {
        Helpers.validateStringLength(value, 1, "VersionCode");
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}