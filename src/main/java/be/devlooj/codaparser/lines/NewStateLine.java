package be.devlooj.codaparser.lines;

import be.devlooj.codaparser.values.*;

public class NewStateLine implements LineInterface {
    private final StatementSequenceNumber statementSequenceNumber;
    private final AccountFull account;
    private final Amount balance;
    private final Date date;

    public NewStateLine(
            StatementSequenceNumber statementSequenceNumber,
            AccountFull account,
            Amount balance,
            Date date
    ) {
        this.statementSequenceNumber = statementSequenceNumber;
        this.account = account;
        this.balance = balance;
        this.date = date;
    }

    public LineType getType() {
        return LineType.NEW_STATE;
    }

    public StatementSequenceNumber getStatementSequenceNumber() {
        return statementSequenceNumber;
    }

    public AccountFull getAccount() {
        return account;
    }

    public Amount getBalance() {
        return balance;
    }

    public Date getDate() {
        return date;
    }
}