package be.devlooj.codaparser.lineparsers;

import be.devlooj.codaparser.lines.TransactionPart2Line;
import be.devlooj.codaparser.values.*;

public class TransactionPart2LineParser implements LineParserInterface {

    @Override
    public TransactionPart2Line parse(String codaLine) {
        return new TransactionPart2Line(
                new SequenceNumber(codaLine.substring(2, 6)),
                new SequenceNumberDetail(codaLine.substring(6, 10)),
                new Message(codaLine.substring(10, 63)),
                new ClientReference(codaLine.substring(63, 98)),
                new Bic(codaLine.substring(98, 109)),
                new TransactionCodeType(codaLine.substring(112, 113)),
                new IsoReasonReturnCode(codaLine.substring(113, 117)),
                new CategoryPurpose(codaLine.substring(117, 121)),
                new Purpose(codaLine.substring(121, 125))
        );
    }

    @Override
    public boolean canAcceptString(String codaLine) {
        return codaLine.length() == 128 && codaLine.startsWith("22");
    }
}