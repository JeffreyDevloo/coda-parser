package be.devlooj.codaparser.values;

import be.devlooj.codaparser.Helpers;

public class ApplicationCode {
    private final String value;

    public ApplicationCode(String value) {
        Helpers.validateStringLength(value, 2, "ApplicationCode");
        this.value = value.trim();
    }

    public String getValue() {
        return value;
    }
}