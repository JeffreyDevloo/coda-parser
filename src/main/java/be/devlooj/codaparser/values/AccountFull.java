package be.devlooj.codaparser.values;


import be.devlooj.codaparser.Helpers;

public class AccountFull {
    private final String value;

    public AccountFull(String value) {
        Helpers.validateStringLength(value, 37, "AccountFull");
        this.value = value.trim();
    }

    public String getValue() {
        return value;
    }
}