package be.devlooj.codaparser.lineparsers;

import be.devlooj.codaparser.lines.NewStateLine;
import be.devlooj.codaparser.values.AccountFull;
import be.devlooj.codaparser.values.Amount;
import be.devlooj.codaparser.values.Date;
import be.devlooj.codaparser.values.StatementSequenceNumber;

public class NewStateLineParser implements LineParserInterface {

    @Override
    public NewStateLine parse(String codaLine) {
        return new NewStateLine(
                new StatementSequenceNumber(codaLine.substring(1, 4)),
                new AccountFull(codaLine.substring(4, 41)), // don't further parse info as it is already present in coda1-line
                new Amount(codaLine.substring(41, 57), true),
                new Date(codaLine.substring(57, 63))
        );
    }

    @Override
    public boolean canAcceptString(String codaLine) {
        return codaLine.length() == 128 && codaLine.charAt(0) == '8';
    }
}