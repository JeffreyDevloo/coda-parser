package be.devlooj.codaparser.lineparsers;

import be.devlooj.codaparser.lines.InformationPart1Line;
import be.devlooj.codaparser.values.*;

public class InformationPart1LineParser implements LineParserInterface {

    @Override
    public InformationPart1Line parse(String codaLine) {
        TransactionCode transactionCode = new TransactionCode(codaLine.substring(31, 39));

        return new InformationPart1Line(
                new SequenceNumber(codaLine.substring(2, 6)),
                new SequenceNumberDetail(codaLine.substring(6, 10)),
                new BankReference(codaLine.substring(10, 31)),
                transactionCode,
                new MessageOrStructuredMessage(codaLine.substring(39, 113), transactionCode)
        );
    }

    @Override
    public boolean canAcceptString(String codaLine) {
        return codaLine.length() == 128 && codaLine.startsWith("31");
    }
}