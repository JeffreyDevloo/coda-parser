package be.devlooj.codaparser.values;

import be.devlooj.codaparser.Helpers;

public class Bic {
    private final String value;

    public Bic(String value) {
        Helpers.validateStringLength(value, 11, "Bic");
        this.value = value.trim();
    }

    public String getValue() {
        return value;
    }
}
