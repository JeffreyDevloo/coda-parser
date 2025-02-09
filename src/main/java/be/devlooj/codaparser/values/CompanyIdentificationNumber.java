package be.devlooj.codaparser.values;

import be.devlooj.codaparser.Helpers;

public class CompanyIdentificationNumber {
    private final String value;

    public CompanyIdentificationNumber(String value) {
        Helpers.validateStringLength(value, 11, "CompanyIdentificationNumber");
        this.value = value.trim();
    }

    public String getValue() {
        return value;
    }
}
