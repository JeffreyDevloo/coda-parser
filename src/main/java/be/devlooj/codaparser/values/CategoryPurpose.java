package be.devlooj.codaparser.values;

import be.devlooj.codaparser.Helpers;

public class CategoryPurpose {
    private final String value;

    public CategoryPurpose(String value) {
        Helpers.validateStringLength(value, 4, "CategoryPurpose");
        this.value = value.trim();
    }

    public String getValue() {
        return value;
    }
}