package be.devlooj.codaparser.statements;

import be.devlooj.codaparser.values.SepaDirectDebit;

import java.time.LocalDate;


public class Transaction {
    private final AccountOtherParty account;
    private final int statementSequence;
    private final int transactionSequence;
    private final int transactionSequenceDetail;
    private final LocalDate transactionDate;
    private final LocalDate valutaDate;
    private final double amount;
    private final String message;
    private final String structuredMessage;
    private final SepaDirectDebit sepaDirectDebit;
    private final TransactionCode transactionCode;
    private final String clientReference;

    public Transaction(
            AccountOtherParty account,
            int statementSequence,
            int transactionSequence,
            int transactionSequenceDetail,
            LocalDate transactionDate,
            LocalDate valutaDate,
            double amount,
            String message,
            String structuredMessage,
            SepaDirectDebit sepaDirectDebit,
            TransactionCode transactionCode,
            String clientReference
    ) {
        this.account = account;
        this.statementSequence = statementSequence;
        this.transactionSequence = transactionSequence;
        this.transactionSequenceDetail = transactionSequenceDetail;
        this.transactionDate = transactionDate;
        this.valutaDate = valutaDate;
        this.amount = amount;
        this.message = message;
        this.structuredMessage = structuredMessage;
        this.sepaDirectDebit = sepaDirectDebit;
        this.transactionCode = transactionCode;
        this.clientReference = clientReference;
    }

    public AccountOtherParty getAccount() {
        return account;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public LocalDate getValutaDate() {
        return valutaDate;
    }

    public double getAmount() {
        return amount;
    }

    public String getMessage() {
        return message;
    }

    public String getStructuredMessage() {
        return structuredMessage;
    }

    public SepaDirectDebit getSepaDirectDebit() {
        return sepaDirectDebit;
    }

    public int getStatementSequence() {
        return statementSequence;
    }

    public int getTransactionSequence() {
        return transactionSequence;
    }

    public int getTransactionSequenceDetail() {
        return transactionSequenceDetail;
    }

    public TransactionCode getTransactionCode() {
        return transactionCode;
    }

    public String getClientReference() {
        return clientReference;
    }
}