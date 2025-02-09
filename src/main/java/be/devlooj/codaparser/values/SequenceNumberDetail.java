package be.devlooj.codaparser.values;

import be.devlooj.codaparser.Helpers;
import be.devlooj.codaparser.exceptions.InvalidValueException;

public class SequenceNumberDetail {
    private final int value;

    public SequenceNumberDetail(String sequenceNumberDetail) {
        Helpers.validateStringLength(sequenceNumberDetail, 4, "SequenceNumberDetail");
        Helpers.validateStringDigitOnly(sequenceNumberDetail, "SequenceNumberDetail");

        int value = Integer.parseInt(sequenceNumberDetail);
        if (value < 0) {
            throw new InvalidValueException("SequenceNumberDetail", sequenceNumberDetail, "Value cannot be negative");
        }

        this.value = value;
    }

    public int getValue() {
        return value;
    }
}