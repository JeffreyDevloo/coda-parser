package be.devlooj.codaparser.lines;

import be.devlooj.codaparser.values.*;

public class TransactionPart1Line implements LineInterface, HasTransactionCode, HasSequenceNumber {
    private final SequenceNumber sequenceNumber;
    private final SequenceNumberDetail sequenceNumberDetail;
    private final BankReference bankReference;
    private final Amount amount;
    private final Date valutaDate;
    private final TransactionCode transactionCode;
    private final MessageOrStructuredMessage messageOrStructuredMessage;
    private final Date transactionDate;
    private final StatementSequenceNumber statementSequenceNumber;
    private final GlobalizationCode globalizationCode;

    public TransactionPart1Line(
            SequenceNumber sequenceNumber,
            SequenceNumberDetail sequenceNumberDetail,
            BankReference bankReference,
            Amount amount,
            Date valutaDate,
            TransactionCode transactionCode,
            MessageOrStructuredMessage messageOrStructuredMessage,
            Date transactionDate,
            StatementSequenceNumber statementSequenceNumber,
            GlobalizationCode globalizationCode
    ) {
        this.sequenceNumber = sequenceNumber;
        this.sequenceNumberDetail = sequenceNumberDetail;
        this.bankReference = bankReference;
        this.amount = amount;
        this.valutaDate = valutaDate;
        this.transactionCode = transactionCode;
        this.messageOrStructuredMessage = messageOrStructuredMessage;
        this.transactionDate = transactionDate;
        this.statementSequenceNumber = statementSequenceNumber;
        this.globalizationCode = globalizationCode;
    }

    @Override
    public LineType getType() {
        return LineType.TRANSACTION_PART1;
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

    public Amount getAmount() {
        return amount;
    }

    public Date getValutaDate() {
        return valutaDate;
    }

    public TransactionCode getTransactionCode() {
        return transactionCode;
    }

    public MessageOrStructuredMessage getMessageOrStructuredMessage() {
        return messageOrStructuredMessage;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public StatementSequenceNumber getStatementSequenceNumber() {
        return statementSequenceNumber;
    }

    public GlobalizationCode getGlobalizationCode() {
        return globalizationCode;
    }
}