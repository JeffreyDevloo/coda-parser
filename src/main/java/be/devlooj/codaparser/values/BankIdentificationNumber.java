package be.devlooj.codaparser.values;

import be.devlooj.codaparser.Helpers;

public class BankIdentificationNumber {
    private final String value;

    public BankIdentificationNumber(String value) {
        Helpers.validateStringLength(value, 3, "BankIdentificationNumber");
        this.value = value.trim();
    }

    public String getValue() {
        return value;
    }
}