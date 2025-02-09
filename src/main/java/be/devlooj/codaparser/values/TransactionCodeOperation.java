package be.devlooj.codaparser.values;

import be.devlooj.codaparser.Helpers;

public class TransactionCodeOperation {
    private final String value;

    public TransactionCodeOperation(String value) {
        Helpers.validateStringLength(value, 2, "TransactionCodeOperation");
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}