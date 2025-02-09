package be.devlooj.codaparser.lineparsers;

import be.devlooj.codaparser.lines.MessageLine;
import be.devlooj.codaparser.values.Message;
import be.devlooj.codaparser.values.SequenceNumber;
import be.devlooj.codaparser.values.SequenceNumberDetail;

public class MessageLineParser implements LineParserInterface {

    @Override
    public MessageLine parse(String codaLine) {
        return new MessageLine(
                new SequenceNumber(codaLine.substring(2, 6)),
                new SequenceNumberDetail(codaLine.substring(6, 10)),
                new Message(codaLine.substring(32, 112))
        );
    }

    @Override
    public boolean canAcceptString(String codaLine) {
        return codaLine.length() == 128 && codaLine.charAt(0) == '4';
    }
}