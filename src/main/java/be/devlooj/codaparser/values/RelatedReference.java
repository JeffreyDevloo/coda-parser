package be.devlooj.codaparser.values;

import be.devlooj.codaparser.Helpers;

public class RelatedReference {
    private final String value;

    public RelatedReference(String value) {
        Helpers.validateStringLength(value, 16, "RelatedReference");
        this.value = value.trim();
    }

    public String getValue() {
        return value;
    }
}