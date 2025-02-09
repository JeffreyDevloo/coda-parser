package be.devlooj.codaparser.statementsparsers;

import be.devlooj.codaparser.lines.*;
import be.devlooj.codaparser.statements.Account;
import be.devlooj.codaparser.statements.Statement;
import be.devlooj.codaparser.statements.Transaction;
import be.devlooj.codaparser.values.TransactionCode;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StatementParser {

    public Statement parse(List<LineInterface> lines) throws Exception {
        LocalDate date = LocalDate.of(1, 1, 1);
        IdentificationLine identificationLine = (IdentificationLine) getFirstLineOfType(lines, LineType.IDENTIFICATION);
        if (identificationLine != null) {
            date = identificationLine.getCreationDate().getValue();
        }

        double initialBalance = 0.0;
        int sequenceNumber = 0;
        InitialStateLine initialStateLine = (InitialStateLine) getFirstLineOfType(lines, LineType.INITIAL_STATE);
        if (initialStateLine != null) {
            initialBalance = initialStateLine.getBalance().getValue();
            sequenceNumber = initialStateLine.getStatementSequenceNumber().getValue();
        }

        double newBalance = 0.0;
        LocalDate newDate = LocalDate.of(1, 1, 1);
        NewStateLine newStateLine = (NewStateLine) getFirstLineOfType(lines, LineType.NEW_STATE);
        if (newStateLine != null) {
            newBalance = newStateLine.getBalance().getValue();
            newDate = newStateLine.getDate().getValue();
        }

        MessageParser messageParser = new MessageParser();
        String informationalMessage = messageParser.parse(
                filterLinesOfTypes(lines, List.of(LineType.MESSAGE))
        );

        AccountParser accountParser = new AccountParser();
        Account account = accountParser.parse(
                filterLinesOfTypes(lines, List.of(LineType.IDENTIFICATION, LineType.INITIAL_STATE))
        );

        List<List<LineInterface>> transactionLines = groupTransactions(
                filterLinesOfTypes(lines, List.of(
                        LineType.TRANSACTION_PART1,
                        LineType.TRANSACTION_PART2,
                        LineType.TRANSACTION_PART3,
                        LineType.INFORMATION_PART1,
                        LineType.INFORMATION_PART2,
                        LineType.INFORMATION_PART3
                ))
        );

        TransactionParser transactionParser = new TransactionParser();
        List<Transaction> transactions = new ArrayList<>();
        for (List<LineInterface> transactionLine : transactionParser.filter(transactionLines)) {
            transactions.add(transactionParser.parse(transactionLine));
        }

        return new Statement(
                date,
                account,
                sequenceNumber,
                initialBalance,
                newBalance,
                newDate,
                informationalMessage,
                transactions
        );
    }

    private List<List<LineInterface>> groupTransactions(List<LineInterface> lines) {
        List<List<LineInterface>> transactions = new ArrayList<>();
        int idx = -1;
        int sequenceNumber = -1;
        int sequenceNumberDetail = -1;

        for (LineInterface line : lines) {
            HasSequenceNumber hasSequenceNumber;
            if (line instanceof HasSequenceNumber hasSequenceNumber1) {
                hasSequenceNumber = hasSequenceNumber1;
            } else {
                continue;
            }

            boolean isCollectiveTransaction = false;
            boolean isTotalizedDetail = false;
            if (line instanceof HasTransactionCode hasTransactionCode) {
                TransactionCode transactionCode = hasTransactionCode.getTransactionCode();
                isCollectiveTransaction = transactionCode.getOperation().getValue().equals("07");
                isTotalizedDetail = line.getType() == LineType.TRANSACTION_PART1
                        && (transactionCode.getType().getValue().equals("5")
                        || transactionCode.getType().getValue().equals("6")
                        || transactionCode.getType().getValue().equals("7"));
            }

            if (transactions.isEmpty()
                    || sequenceNumber != hasSequenceNumber.getSequenceNumber().getValue()
                    || (isCollectiveTransaction && sequenceNumberDetail != hasSequenceNumber.getSequenceNumberDetail().getValue())
                    || (isTotalizedDetail && sequenceNumberDetail != hasSequenceNumber.getSequenceNumberDetail().getValue())) {
                sequenceNumber = hasSequenceNumber.getSequenceNumber().getValue();
                sequenceNumberDetail = hasSequenceNumber.getSequenceNumberDetail().getValue();
                idx += 1;
                transactions.add(new ArrayList<>());
            }

            transactions.get(idx).add(line);
        }

        return transactions;
    }

    private LineInterface getFirstLineOfType(List<LineInterface> lines, LineType type) {
        for (LineInterface line : lines) {
            if (line.getType().equals(type)) {
                return line;
            }
        }
        return null;
    }

    private <T extends LineInterface> List<T> filterLinesOfTypes(List<LineInterface> lines, List<LineType> types) {
        List<T> filteredLines = new ArrayList<>();
        for (LineInterface line : lines) {
            if (types.contains(line.getType())) {
                filteredLines.add((T) line);
            }
        }
        return filteredLines;
    }
}