package be.devlooj.codaparser.values;

import be.devlooj.codaparser.Helpers;

public class TransactionCodeCategory {
    private final String value;

    public TransactionCodeCategory(String value) {
        Helpers.validateStringLength(value, 3, "TransactionCodeCategory");
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}