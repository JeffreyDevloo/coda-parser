package be.devlooj.codaparser.statements;

public class AccountOtherParty {
    private final String name;
    private final String bic;
    private final String number;
    private final String currencyCode;

    public AccountOtherParty(String name, String bic, String number, String currencyCode) {
        this.name = name;
        this.bic = bic;
        this.number = number;
        this.currencyCode = currencyCode;
    }

    public String getName() {
        return name;
    }

    public String getBic() {
        return bic;
    }

    public String getNumber() {
        return number;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }
}