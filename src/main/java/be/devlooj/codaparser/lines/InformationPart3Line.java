package be.devlooj.codaparser.lines;

import be.devlooj.codaparser.values.*;

public class InformationPart3Line implements LineInterface, HasSequenceNumber {
    private final SequenceNumber sequenceNumber;
    private final SequenceNumberDetail sequenceNumberDetail;
    private final Message message;

    public InformationPart3Line(
            SequenceNumber sequenceNumber,
            SequenceNumberDetail sequenceNumberDetail,
            Message message
    ) {
        this.sequenceNumber = sequenceNumber;
        this.sequenceNumberDetail = sequenceNumberDetail;
        this.message = message;
    }

    @Override
    public LineType getType() {
        return LineType.INFORMATION_PART3;
    }

    @Override
    public SequenceNumber getSequenceNumber() {
        return sequenceNumber;
    }

    @Override
    public SequenceNumberDetail getSequenceNumberDetail() {
        return sequenceNumberDetail;
    }

    public Message getMessage() {
        return message;
    }
}