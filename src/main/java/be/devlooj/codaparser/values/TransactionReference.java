package be.devlooj.codaparser.values;

import be.devlooj.codaparser.Helpers;

public class TransactionReference {
    private final String value;

    public TransactionReference(String value) {
        Helpers.validateStringLength(value, 16, "TransactionReference");
        this.value = value.trim();
    }

    public String getValue() {
        return value;
    }
}