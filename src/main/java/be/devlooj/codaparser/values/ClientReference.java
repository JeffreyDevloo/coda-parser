package be.devlooj.codaparser.values;

import be.devlooj.codaparser.Helpers;

public class ClientReference {
    private final String value;

    public ClientReference(String value) {
        Helpers.validateStringLength(value, 35, "ClientReference");
        this.value = value.trim();
    }

    public String getValue() {
        return value;
    }
}