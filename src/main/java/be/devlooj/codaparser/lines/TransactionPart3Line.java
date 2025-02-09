package be.devlooj.codaparser.lines;

import be.devlooj.codaparser.values.*;

public class TransactionPart3Line implements LineInterface, HasSequenceNumber {
    private final SequenceNumber sequenceNumber;
    private final SequenceNumberDetail sequenceNumberDetail;
    private final AccountFull otherAccountNumberAndCurrency;
    private final AccountName otherAccountName;
    private final Message message;

    public TransactionPart3Line(
            SequenceNumber sequenceNumber,
            SequenceNumberDetail sequenceNumberDetail,
            AccountFull otherAccountNumberAndCurrency,
            AccountName otherAccountName,
            Message message
    ) {
        this.sequenceNumber = sequenceNumber;
        this.sequenceNumberDetail = sequenceNumberDetail;
        this.otherAccountNumberAndCurrency = otherAccountNumberAndCurrency;
        this.otherAccountName = otherAccountName;
        this.message = message;
    }

    @Override
    public LineType getType() {
        return LineType.TRANSACTION_PART3;
    }

    @Override
    public SequenceNumber getSequenceNumber() {
        return sequenceNumber;
    }

    @Override
    public SequenceNumberDetail getSequenceNumberDetail() {
        return sequenceNumberDetail;
    }

    public AccountFull getOtherAccountNumberAndCurrency() {
        return otherAccountNumberAndCurrency;
    }

    public AccountName getOtherAccountName() {
        return otherAccountName;
    }

    public Message getMessage() {
        return message;
    }
}