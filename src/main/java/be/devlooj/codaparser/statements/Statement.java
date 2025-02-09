package be.devlooj.codaparser.statements;

import java.time.LocalDate;
import java.util.List;

public class Statement {
    private final LocalDate date;
    private final Account account;
    private final int sequenceNumber;
    private final double initialBalance;
    private final double newBalance;
    private final LocalDate newDate;
    private final String informationalMessage;
    private final List<Transaction> transactions;

    public Statement(LocalDate date, Account account, int sequenceNumber, double initialBalance, double newBalance, LocalDate newDate, String informationalMessage, List<Transaction> transactions) {
        this.date = date;
        this.account = account;
        this.sequenceNumber = sequenceNumber;
        this.initialBalance = initialBalance;
        this.newBalance = newBalance;
        this.newDate = newDate;
        this.informationalMessage = informationalMessage;
        this.transactions = transactions;
    }

    public LocalDate getDate() {
        return date;
    }

    public Account getAccount() {
        return account;
    }

    public int getSequenceNumber() {
        return sequenceNumber;
    }

    public double getInitialBalance() {
        return initialBalance;
    }

    public double getNewBalance() {
        return newBalance;
    }

    public LocalDate getNewDate() {
        return newDate;
    }

    public String getInformationalMessage() {
        return informationalMessage;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }
}