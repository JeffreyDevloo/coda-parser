package be.devlooj.codaparser.values;

import be.devlooj.codaparser.Helpers;

import java.util.List;

public class StructuredMessage {
    private final String structuredMessageType;
    private String structuredMessage = "";
    private final String structuredMessageFull;
    private SepaDirectDebit sepaDirectDebit = null;

    public StructuredMessage(String value, TransactionCode transactionCode) {
        Helpers.validateStringMultipleLengths(value, List.of(53, 73), "StructuredMessage");

        this.structuredMessageType = value.substring(0, 3);
        this.structuredMessageFull = value.substring(3);

        if (this.structuredMessageType.equals("101") || this.structuredMessageType.equals("102")) {
            this.structuredMessage = this.structuredMessageFull.substring(0, 12);
        } else if (this.structuredMessageType.equals("105") && this.structuredMessageFull.length() >= 57) {
            this.structuredMessage = this.structuredMessageFull.substring(45, 57); // is start position 42 or 45?
        } else if (this.structuredMessageType.equals("127") && transactionCode.getFamily().getValue().equals("05")) {
            this.sepaDirectDebit = new SepaDirectDebit(this.structuredMessageFull);
        }
    }

    public String getType() {
        return structuredMessageType;
    }

    public String getStructuredMessage() {
        return structuredMessage;
    }

    public String getAll() {
        return structuredMessageFull;
    }

    public SepaDirectDebit getSepaDirectDebit() {
        return sepaDirectDebit;
    }
}