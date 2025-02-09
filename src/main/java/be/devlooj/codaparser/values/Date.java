package be.devlooj.codaparser.values;

import be.devlooj.codaparser.Helpers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Date {
    final static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private final LocalDate value;

    public Date(String dateString) {
        Helpers.validateStringLength(dateString, 6, "Date");
        Helpers.validateStringDigitOnly(dateString, "Date");
        value = LocalDate.parse(Helpers.formatDateString(dateString), formatter);
    }

    public LocalDate getValue() {
        return value;
    }
}
