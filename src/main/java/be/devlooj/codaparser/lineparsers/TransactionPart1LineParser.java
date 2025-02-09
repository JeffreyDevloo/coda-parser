package be.devlooj.codaparser.lineparsers;

import be.devlooj.codaparser.lines.TransactionPart1Line;
import be.devlooj.codaparser.values.*;

public class TransactionPart1LineParser implements LineParserInterface {

    @Override
    public TransactionPart1Line parse(String codaLine) {
        TransactionCode transactionCode = new TransactionCode(codaLine.substring(53, 61));

        return new TransactionPart1Line(
                new SequenceNumber(codaLine.substring(2, 6)),
                new SequenceNumberDetail(codaLine.substring(6, 10)),
                new BankReference(codaLine.substring(10, 31)),
                new Amount(codaLine.charAt(31) + codaLine.substring(32, 47), true),
                new Date(codaLine.substring(47, 53)),
                transactionCode,
                new MessageOrStructuredMessage(codaLine.substring(61, 115), transactionCode),
                new Date(codaLine.substring(115, 121)),
                new StatementSequenceNumber(codaLine.substring(121, 124)),
                new GlobalizationCode(codaLine.substring(124, 125))
        );
    }

    @Override
    public boolean canAcceptString(String codaLine) {
        return codaLine.length() == 128 && codaLine.startsWith("21");
    }
}