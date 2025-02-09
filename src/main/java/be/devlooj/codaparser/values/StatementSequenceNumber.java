package be.devlooj.codaparser.values;

import be.devlooj.codaparser.Helpers;

public class StatementSequenceNumber {
    private final int value;

    public StatementSequenceNumber(String value) {
        Helpers.validateStringLength(value, 3, "StatementSequenceNumber");
        Helpers.validateStringDigitOnly(value, "StatementSequenceNumber");

        this.value = Integer.parseInt(value);
    }

    public int getValue() {
        return value;
    }
}