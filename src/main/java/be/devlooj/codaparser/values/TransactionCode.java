package be.devlooj.codaparser.values;

import be.devlooj.codaparser.Helpers;

public class TransactionCode {
    private final TransactionCodeFamily family;
    private final TransactionCodeType type;
    private final TransactionCodeOperation operation;
    private final TransactionCodeCategory category;

    public TransactionCode(String value) {
        Helpers.validateStringLength(value, 8, "TransactionCode");
        Helpers.validateStringDigitOnly(value, "TransactionCode");

        this.type = new TransactionCodeType(value.substring(0, 1));
        this.family = new TransactionCodeFamily(value.substring(1, 3));
        this.operation = new TransactionCodeOperation(value.substring(3, 5));
        this.category = new TransactionCodeCategory(value.substring(5, 8));
    }

    public TransactionCodeType getType() {
        return type;
    }

    public TransactionCodeFamily getFamily() {
        return family;
    }

    public TransactionCodeOperation getOperation() {
        return operation;
    }

    public TransactionCodeCategory getCategory() {
        return category;
    }
}