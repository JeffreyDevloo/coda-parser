package be.devlooj.codaparser.statementsparsers;

import be.devlooj.codaparser.lines.*;
import be.devlooj.codaparser.statements.Account;
import java.util.List;

public class AccountParser {

    public Account parse(List<LineInterface> lines) {
        IdentificationLine identificationLine = (IdentificationLine) getFirstLineOfType(lines, LineType.IDENTIFICATION);
        InitialStateLine initialStateLine = (InitialStateLine) getFirstLineOfType(lines, LineType.INITIAL_STATE);

        return new Account(
                initialStateLine != null ? initialStateLine.getAccount().getName().getValue() : "",
                identificationLine != null ? identificationLine.getAccountBic().getValue() : "",
                identificationLine != null ? identificationLine.getAccountCompanyIdentificationNumber().getValue() : "",
                initialStateLine != null ? initialStateLine.getAccount().getNumber().getValue() : "",
                initialStateLine != null ? initialStateLine.getAccount().getCurrency().getCurrencyCode() : "",
                initialStateLine != null ? initialStateLine.getAccount().getCountry().getCountryCode() : "",
                initialStateLine != null ? initialStateLine.getAccount().getDescription().getValue() : ""
        );
    }

    private LineInterface getFirstLineOfType(List<LineInterface> lines, LineType type) {
        for (LineInterface line : lines) {
            if (line.getType().equals(type)) {
                return line;
            }
        }
        return null;
    }
}