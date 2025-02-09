package be.devlooj.codaparser.values;


import be.devlooj.codaparser.Helpers;

public class Account {
    private final AccountName accountName;
    private final AccountDescription accountDescription;
    private final AccountNumberType accountNumberType;
    private final AccountNumber accountNumber;
    private final Currency accountCurrency;
    private final Country accountCountry;

    public Account(String accountNumberTypeString, String accountInfo, String accountNameInfo) {
        Helpers.validateStringLength(accountInfo, 37, "Account");
        Helpers.validateStringLength(accountNameInfo, 61, "AccountNameInfo");

        Object[] accountInfoArray = addAccountInfo(accountInfo, accountNumberTypeString);
        boolean accountIsIban = (boolean) accountInfoArray[0];
        String accountNumber = (String) accountInfoArray[1];
        String accountCurrency = (String) accountInfoArray[2];
        String accountCountry = (String) accountInfoArray[3];

        this.accountNumberType = new AccountNumberType(accountNumberTypeString);
        this.accountName = new AccountName(accountNameInfo.substring(0, 26));
        this.accountDescription = new AccountDescription(accountNameInfo.substring(26, 61));
        this.accountNumber = new AccountNumber(accountNumber, accountIsIban);
        this.accountCurrency = new Currency(accountCurrency);
        this.accountCountry = new Country(accountCountry);
    }

    private Object[] addAccountInfo(String accountInfo, String accountType) {
        boolean accountIsIban = false;
        String accountNumber = "";
        String accountCurrency = "";
        String accountCountry = "";

        switch (accountType) {
            case "0":
                accountNumber = accountInfo.substring(0, 12);
                accountCurrency = accountInfo.substring(13, 16);
                accountCountry = accountInfo.substring(17, 19);
                break;
            case "1":
                accountNumber = accountInfo.substring(0, 34);
                accountCurrency = accountInfo.substring(34, 37);
                break;
            case "2":
                accountIsIban = true;
                accountNumber = accountInfo.substring(0, 31);
                accountCurrency = accountInfo.substring(34, 37);
                accountCountry = "BE";
                break;
            case "3":
                accountIsIban = true;
                accountNumber = accountInfo.substring(0, 34);
                accountCurrency = accountInfo.substring(34, 37);
                break;
        }

        return new Object[]{accountIsIban, accountNumber, accountCurrency, accountCountry};
    }

    public AccountName getName() {
        return accountName;
    }

    public AccountDescription getDescription() {
        return accountDescription;
    }

    public AccountNumberType getNumberType() {
        return accountNumberType;
    }

    public AccountNumber getNumber() {
        return accountNumber;
    }

    public Currency getCurrency() {
        return accountCurrency;
    }

    public Country getCountry() {
        return accountCountry;
    }
}