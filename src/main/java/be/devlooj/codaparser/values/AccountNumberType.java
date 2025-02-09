package be.devlooj.codaparser.values;

import be.devlooj.codaparser.Helpers;

public class AccountNumberType {
    private final String value;

    public AccountNumberType(String value) {
        Helpers.validateStringLength(value, 1, "AccountNumberType");
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
