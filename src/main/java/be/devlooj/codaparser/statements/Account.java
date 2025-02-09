package be.devlooj.codaparser.statements;

public class Account {
    private final String name;
    private final String bic;
    private final String companyIdentificationNumber;
    private final String number;
    private final String currencyCode;
    private final String countryCode;
    private final String description;

    public Account(String name, String bic, String companyIdentificationNumber, String number, String currencyCode, String countryCode, String description) {
        this.name = name;
        this.bic = bic;
        this.companyIdentificationNumber = companyIdentificationNumber;
        this.number = number;
        this.currencyCode = currencyCode;
        this.countryCode = countryCode;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getBic() {
        return bic;
    }

    public String getCompanyIdentificationNumber() {
        return companyIdentificationNumber;
    }

    public String getNumber() {
        return number;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getDescription() {
        return description;
    }
}