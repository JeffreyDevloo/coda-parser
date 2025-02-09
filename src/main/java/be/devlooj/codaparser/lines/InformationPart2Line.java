package be.devlooj.codaparser.lines;

import be.devlooj.codaparser.values.*;

public class InformationPart2Line implements LineInterface, HasSequenceNumber {
    private final SequenceNumber sequenceNumber;
    private final SequenceNumberDetail sequenceNumberDetail;
    private final Message message;

    public InformationPart2Line(
            SequenceNumber sequenceNumber,
            SequenceNumberDetail sequenceNumberDetail,
            Message message
    ) {
        this.sequenceNumber = sequenceNumber;
        this.sequenceNumberDetail = sequenceNumberDetail;
        this.message = message;
    }

    public LineType getType() {
        return LineType.INFORMATION_PART2;
    }

    public SequenceNumber getSequenceNumber() {
        return sequenceNumber;
    }

    public SequenceNumberDetail getSequenceNumberDetail() {
        return sequenceNumberDetail;
    }

    public Message getMessage() {
        return message;
    }
}