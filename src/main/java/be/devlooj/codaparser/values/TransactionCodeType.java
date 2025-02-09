package be.devlooj.codaparser.values;

import be.devlooj.codaparser.Helpers;

public class TransactionCodeType {
    private final String value;

    public TransactionCodeType(String value) {
        Helpers.validateStringLength(value, 1, "TransactionCodeType");
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}