package be.devlooj.codaparser.lines;

import be.devlooj.codaparser.values.*;

public class InitialStateLine implements LineInterface {
    private final PaperStatementSequenceNumber paperStatementSequenceNumber;
    private final StatementSequenceNumber statementSequenceNumber;
    private final Account account;
    private final Amount balance;
    private final Date date;

    public InitialStateLine(
            PaperStatementSequenceNumber paperStatementSequenceNumber,
            StatementSequenceNumber statementSequenceNumber,
            Account account,
            Amount balance,
            Date date
    ) {
        this.paperStatementSequenceNumber = paperStatementSequenceNumber;
        this.statementSequenceNumber = statementSequenceNumber;
        this.account = account;
        this.balance = balance;
        this.date = date;
    }

    public LineType getType() {
        return LineType.INITIAL_STATE;
    }

    public PaperStatementSequenceNumber getPaperStatementSequenceNumber() {
        return paperStatementSequenceNumber;
    }

    public StatementSequenceNumber getStatementSequenceNumber() {
        return statementSequenceNumber;
    }

    public Account getAccount() {
        return account;
    }

    public Amount getBalance() {
        return balance;
    }

    public Date getDate() {
        return date;
    }
}