package be.devlooj.codaparser.lines;

import be.devlooj.codaparser.values.SequenceNumber;
import be.devlooj.codaparser.values.SequenceNumberDetail;

public interface HasSequenceNumber {
    SequenceNumber getSequenceNumber();

    SequenceNumberDetail getSequenceNumberDetail();

}
