package be.devlooj.codaparser.values;

import be.devlooj.codaparser.Helpers;
import be.devlooj.codaparser.exceptions.InvalidValueException;

public class PaperStatementSequenceNumber {
    private final int value;

    public PaperStatementSequenceNumber(String paperStatementSequenceNumber) {
        Helpers.validateStringLength(paperStatementSequenceNumber, 3, "PaperStatementSequenceNumber");
        Helpers.validateStringDigitOnly(paperStatementSequenceNumber, "PaperStatementSequenceNumber");

        int value = Integer.parseInt(paperStatementSequenceNumber);
        if (value < 0) {
            throw new InvalidValueException("PaperStatementSequenceNumber", paperStatementSequenceNumber, "Value cannot be negative");
        }

        this.value = value;
    }

    public int getValue() {
        return value;
    }
}