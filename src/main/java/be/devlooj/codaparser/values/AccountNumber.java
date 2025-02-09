package be.devlooj.codaparser.values;

public class AccountNumber {
    private final String value;
    private final boolean isIbanNumber;

    public AccountNumber(String value, boolean isIbanNumber) {
        this.value = value;
        this.isIbanNumber = isIbanNumber;
    }

    public boolean isIbanNumber() {
        return isIbanNumber;
    }

    public String getValue() {
        return value;
    }
}