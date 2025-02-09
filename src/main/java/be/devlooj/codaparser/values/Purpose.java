package be.devlooj.codaparser.values;

import be.devlooj.codaparser.Helpers;

public class Purpose {
    private final String value;

    public Purpose(String value) {
        Helpers.validateStringLength(value, 4, "Purpose");
        this.value = value.trim();
    }

    public String getValue() {
        return value;
    }
}
