package be.devlooj.codaparser.lines;

import be.devlooj.codaparser.values.*;

public class MessageLine implements LineInterface {
    private final SequenceNumber sequenceNumber;
    private final SequenceNumberDetail sequenceNumberDetail;
    private final Message content;

    public MessageLine(
            SequenceNumber sequenceNumber,
            SequenceNumberDetail sequenceNumberDetail,
            Message content
    ) {
        this.sequenceNumber = sequenceNumber;
        this.sequenceNumberDetail = sequenceNumberDetail;
        this.content = content;
    }

    public LineType getType() {
        return LineType.MESSAGE;
    }

    public SequenceNumber getSequenceNumber() {
        return sequenceNumber;
    }

    public SequenceNumberDetail getSequenceNumberDetail() {
        return sequenceNumberDetail;
    }

    public Message getContent() {
        return content;
    }
}