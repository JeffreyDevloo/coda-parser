package be.devlooj.codaparser.values;


import be.devlooj.codaparser.Helpers;

public class AccountDescription {
    private final String value;

    public AccountDescription(String value) {
        Helpers.validateStringLength(value, 35, "AccountDescription");
        this.value = value.trim();
    }

    public String getValue() {
        return value;
    }
}