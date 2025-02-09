package be.devlooj.codaparser.values;

public class Currency {
    private final String currencyCode;

    public Currency(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }
}
