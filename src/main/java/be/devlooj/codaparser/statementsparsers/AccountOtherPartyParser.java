package be.devlooj.codaparser.statementsparsers;

import be.devlooj.codaparser.lines.*;
import be.devlooj.codaparser.statements.AccountOtherParty;
import java.util.List;

public class AccountOtherPartyParser {

    public AccountOtherParty parse(List<LineInterface> lines) {
        TransactionPart2Line transactionPart2Line = (TransactionPart2Line) getFirstLineOfType(lines, LineType.TRANSACTION_PART2);
        TransactionPart3Line transactionPart3Line = (TransactionPart3Line) getFirstLineOfType(lines, LineType.TRANSACTION_PART3);

        String bic = "";
        if (transactionPart2Line != null) {
            bic = transactionPart2Line.getOtherAccountBic().getValue();
        }

        String number = "";
        String name = "";
        String currency = "";
        if (transactionPart3Line != null) {
            name = transactionPart3Line.getOtherAccountName().getValue();
            number = transactionPart3Line.getOtherAccountNumberAndCurrency().getValue();
            if (number != null && !number.isEmpty()) {
                int lastSpace = number.lastIndexOf(" ");
                if (lastSpace != -1) {
                    currency = number.substring(lastSpace).trim();
                    number = number.substring(0, lastSpace).trim();
                }
            }
        }

        return new AccountOtherParty(name, bic, number, currency);
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