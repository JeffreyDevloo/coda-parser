package be.devlooj.codaparser.lineparsers;

import be.devlooj.codaparser.lines.InitialStateLine;
import be.devlooj.codaparser.values.*;

public class InitialStateLineParser implements LineParserInterface {

    @Override
    public InitialStateLine parse(String codaLine) {
        return new InitialStateLine(
                new PaperStatementSequenceNumber(codaLine.substring(125, 128)),
                new StatementSequenceNumber(codaLine.substring(2, 5)),
                new Account(codaLine.substring(1, 2), codaLine.substring(5, 42), codaLine.substring(64, 125)),
                new Amount(codaLine.substring(42, 58), true),
                new Date(codaLine.substring(58, 64))
        );
    }

    @Override
    public boolean canAcceptString(String codaLine) {
        return codaLine.length() == 128 && codaLine.charAt(0) == '1';
    }
}