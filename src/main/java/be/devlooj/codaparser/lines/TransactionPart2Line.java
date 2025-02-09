package be.devlooj.codaparser.lines;

import be.devlooj.codaparser.values.*;

public class TransactionPart2Line implements LineInterface, HasSequenceNumber{
    private final SequenceNumber sequenceNumber;
    private final SequenceNumberDetail sequenceNumberDetail;
    private final Message message;
    private final ClientReference clientReference;
    private final Bic otherAccountBic;
    private final TransactionCodeType transactionType;
    private final IsoReasonReturnCode isoReasonReturnCode;
    private final CategoryPurpose categoryPurpose;
    private final Purpose purpose;

    public TransactionPart2Line(
            SequenceNumber sequenceNumber,
            SequenceNumberDetail sequenceNumberDetail,
            Message message,
            ClientReference clientReference,
            Bic otherAccountBic,
            TransactionCodeType transactionType,
            IsoReasonReturnCode isoReasonReturnCode,
            CategoryPurpose categoryPurpose,
            Purpose purpose
    ) {
        this.sequenceNumber = sequenceNumber;
        this.sequenceNumberDetail = sequenceNumberDetail;
        this.message = message;
        this.clientReference = clientReference;
        this.otherAccountBic = otherAccountBic;
        this.transactionType = transactionType;
        this.isoReasonReturnCode = isoReasonReturnCode;
        this.categoryPurpose = categoryPurpose;
        this.purpose = purpose;
    }

    @Override
    public LineType getType() {
        return LineType.TRANSACTION_PART2;
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

    public ClientReference getClientReference() {
        return clientReference;
    }

    public Bic getOtherAccountBic() {
        return otherAccountBic;
    }

    public TransactionCodeType getTransactionType() {
        return transactionType;
    }

    public IsoReasonReturnCode getIsoReasonReturnCode() {
        return isoReasonReturnCode;
    }

    public CategoryPurpose getCategoryPurpose() {
        return categoryPurpose;
    }

    public Purpose getPurpose() {
        return purpose;
    }
}