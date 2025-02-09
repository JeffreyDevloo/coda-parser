package be.devlooj.codaparser.lines;

import be.devlooj.codaparser.values.*;

public class InformationPart1Line implements LineInterface, HasTransactionCode, HasSequenceNumber {
    private final SequenceNumber sequenceNumber;
    private final SequenceNumberDetail sequenceNumberDetail;
    private final BankReference bankReference;
    private final TransactionCode transactionCode;
    private final MessageOrStructuredMessage messageOrStructuredMessage;

    public InformationPart1Line(
            SequenceNumber sequenceNumber,
            SequenceNumberDetail sequenceNumberDetail,
            BankReference bankReference,
            TransactionCode transactionCode,
            MessageOrStructuredMessage messageOrStructuredMessage
    ) {
        this.sequenceNumber = sequenceNumber;
        this.sequenceNumberDetail = sequenceNumberDetail;
        this.bankReference = bankReference;
        this.transactionCode = transactionCode;
        this.messageOrStructuredMessage = messageOrStructuredMessage;
    }

    @Override
    public LineType getType() {
        return LineType.INFORMATION_PART1;
    }

    @Override
    public SequenceNumber getSequenceNumber() {
        return sequenceNumber;
    }

    @Override
    public SequenceNumberDetail getSequenceNumberDetail() {
        return sequenceNumberDetail;
    }

    public BankReference getBankReference() {
        return bankReference;
    }

    public TransactionCode getTransactionCode() {
        return transactionCode;
    }

    public MessageOrStructuredMessage getMessageOrStructuredMessage() {
        return messageOrStructuredMessage;
    }
}