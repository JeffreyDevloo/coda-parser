package be.devlooj.codaparser.values;

import be.devlooj.codaparser.Helpers;

public class IsoReasonReturnCode {
    private final String value;

    public IsoReasonReturnCode(String value) {
        Helpers.validateStringLength(value, 4, "IsoReasonReturnCode");
        this.value = value.trim();
    }

    public String getValue() {
        return value;
    }
}