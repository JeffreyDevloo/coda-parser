package be.devlooj.codaparser.values;

import be.devlooj.codaparser.Helpers;
import be.devlooj.codaparser.exceptions.InvalidValueException;

public class SequenceNumber {
    private final int value;

    public SequenceNumber(String sequenceNumber) {
        Helpers.validateStringLength(sequenceNumber, 4, "SequenceNumber");
        Helpers.validateStringDigitOnly(sequenceNumber, "SequenceNumber");

        int value = Integer.parseInt(sequenceNumber);
        if (value < 0) {
            throw new InvalidValueException("SequenceNumber", sequenceNumber, "Value cannot be negative");
        }

        this.value = value;
    }

    public int getValue() {
        return value;
    }
}