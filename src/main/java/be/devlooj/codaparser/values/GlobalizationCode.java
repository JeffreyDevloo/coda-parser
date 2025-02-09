package be.devlooj.codaparser.values;

import be.devlooj.codaparser.Helpers;

public class GlobalizationCode {
    private final int value;

    public GlobalizationCode(String value) {
        Helpers.validateStringLength(value, 1, "GlobalizationCode");
        this.value = Integer.parseInt(value);
    }

    public int getValue() {
        return value;
    }
}