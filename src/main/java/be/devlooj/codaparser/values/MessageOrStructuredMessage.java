package be.devlooj.codaparser.values;

import be.devlooj.codaparser.Helpers;

import java.util.List;

public class MessageOrStructuredMessage {
    private final StructuredMessage structuredMessage;
    private final Message message;

    public MessageOrStructuredMessage(String value, TransactionCode transactionCode) {
        Helpers.validateStringMultipleLengths(value, List.of(54, 74), "MessageOrStructuredMessage");

        boolean hasStructuredMessage = value.startsWith("1");

        if (hasStructuredMessage) {
            this.structuredMessage = new StructuredMessage(value.substring(1), transactionCode);
            this.message = null;
        } else {
            this.structuredMessage = null;
            this.message = new Message(value.substring(1));
        }
    }

    public StructuredMessage getStructuredMessage() {
        return structuredMessage;
    }

    public Message getMessage() {
        return message;
    }
}