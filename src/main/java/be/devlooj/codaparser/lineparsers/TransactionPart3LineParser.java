package be.devlooj.codaparser.lineparsers;

import be.devlooj.codaparser.lines.TransactionPart3Line;
import be.devlooj.codaparser.values.*;

public class TransactionPart3LineParser implements LineParserInterface {

    @Override
    public TransactionPart3Line parse(String codaLine) {
        return new TransactionPart3Line(
                new SequenceNumber(codaLine.substring(2, 6)),
                new SequenceNumberDetail(codaLine.substring(6, 10)),
                new AccountFull(codaLine.substring(10, 47)),
                new AccountName(codaLine.substring(47, 82)),
                new Message(codaLine.substring(82, 125))
        );
    }

    @Override
    public boolean canAcceptString(String codaLine) {
        return codaLine.length() == 128 && codaLine.startsWith("23");
    }
}