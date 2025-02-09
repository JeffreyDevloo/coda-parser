package be.devlooj.codaparser.values;

import static be.devlooj.codaparser.Helpers.validateStringDigitOnly;
import static be.devlooj.codaparser.Helpers.validateStringLength;

public class Amount {
    private final double value;

    public Amount(String amountAsString) {
        this(amountAsString, false);
    }

    public Amount(String amountAsString, boolean includesSign) {
        validateStringDigitOnly(amountAsString, "Amount");

        int negative = 1;
        if (includesSign) {
            validateStringLength(amountAsString, 16, "Amount");

            negative = amountAsString.charAt(0) == '1' ? -1 : 1;
            amountAsString = amountAsString.substring(1, 16);
        } else {
            validateStringLength(amountAsString, 15, "Amount");
        }

        this.value = Double.parseDouble(amountAsString) * negative / 1000;
    }

    public double getValue() {
        return value;
    }
}