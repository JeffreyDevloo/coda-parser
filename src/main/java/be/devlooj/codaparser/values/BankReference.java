package be.devlooj.codaparser.values;

import be.devlooj.codaparser.Helpers;

public class BankReference {
    private final String value;

    public BankReference(String value) {
        Helpers.validateStringLength(value, 21, "BankReference");
        this.value = value.trim();
    }

    public String getValue() {
        return value;
    }
}