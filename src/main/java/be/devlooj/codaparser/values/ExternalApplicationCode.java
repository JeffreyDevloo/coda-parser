package be.devlooj.codaparser.values;

import be.devlooj.codaparser.Helpers;

public class ExternalApplicationCode {
    private final String value;

    public ExternalApplicationCode(String value) {
        Helpers.validateStringLength(value, 5, "ExternalApplicationCode");
        this.value = value.trim();
    }

    public String getValue() {
        return value;
    }
}
