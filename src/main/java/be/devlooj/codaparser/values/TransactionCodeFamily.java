package be.devlooj.codaparser.values;

import be.devlooj.codaparser.Helpers;

public class TransactionCodeFamily {
    private final String value;

    public TransactionCodeFamily(String value) {
        Helpers.validateStringLength(value, 2, "TransactionCodeFamily");
        Helpers.validateStringDigitOnly(value, "TransactionCodeFamily");

        this.value = value;
    }

    public String getValue() {
        return value;
    }
}