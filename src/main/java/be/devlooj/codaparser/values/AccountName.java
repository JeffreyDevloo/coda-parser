package be.devlooj.codaparser.values;


import be.devlooj.codaparser.Helpers;

import java.util.Arrays;

public class AccountName {
    private final String value;

    public AccountName(String value) {
        Helpers.validateStringMultipleLengths(value, Arrays.asList(26, 35), "AccountName");
        this.value = value.trim();
    }

    public String getValue() {
        return value;
    }
}