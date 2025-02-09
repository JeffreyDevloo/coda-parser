package be.devlooj.codaparser.statements;

public class TransactionCode {
    private final String family;
    private final String type;
    private final String operation;
    private final String category;

    public TransactionCode(String family, String type, String operation, String category) {
        this.family = family;
        this.type = type;
        this.operation = operation;
        this.category = category;
    }

    public String getFamily() {
        return family;
    }

    public String getType() {
        return type;
    }

    public String getOperation() {
        return operation;
    }

    public String getCategory() {
        return category;
    }

    public static TransactionCode of(be.devlooj.codaparser.values.TransactionCode transactionCode) {
        return new TransactionCode(
                transactionCode.getFamily().getValue(),
                transactionCode.getType().getValue(),
                transactionCode.getOperation().getValue(),
                transactionCode.getCategory().getValue()
        );
    }
}