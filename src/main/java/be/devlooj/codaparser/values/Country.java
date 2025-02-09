package be.devlooj.codaparser.values;

public class Country {
    private final String countryCode;

    public Country(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountryCode() {
        return countryCode;
    }
}