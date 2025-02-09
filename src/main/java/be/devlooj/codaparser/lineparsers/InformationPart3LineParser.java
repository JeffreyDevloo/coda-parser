package be.devlooj.codaparser.lineparsers;

import be.devlooj.codaparser.lines.InformationPart3Line;
import be.devlooj.codaparser.values.Message;
import be.devlooj.codaparser.values.SequenceNumber;
import be.devlooj.codaparser.values.SequenceNumberDetail;

public class InformationPart3LineParser implements LineParserInterface {

    @Override
    public InformationPart3Line parse(String codaLine) {
        return new InformationPart3Line(
                new SequenceNumber(codaLine.substring(2, 6)),
                new SequenceNumberDetail(codaLine.substring(6, 10)),
                new Message(codaLine.substring(10, 100))
        );
    }

    @Override
    public boolean canAcceptString(String codaLine) {
        return codaLine.length() == 128 && codaLine.startsWith("33");
    }
}