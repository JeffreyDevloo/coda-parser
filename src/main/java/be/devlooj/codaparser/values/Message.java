package be.devlooj.codaparser.values;

import be.devlooj.codaparser.Helpers;

public class Message {
    private final String value;

    public Message(String value) {
        this.value = Helpers.trimSpace(value);
    }

    public String getValue() {
        return value;
    }
}