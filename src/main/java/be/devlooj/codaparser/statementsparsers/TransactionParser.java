package be.devlooj.codaparser.statementsparsers;

import be.devlooj.codaparser.lines.*;
import be.devlooj.codaparser.statements.AccountOtherParty;
import be.devlooj.codaparser.statements.Transaction;
import be.devlooj.codaparser.statements.TransactionCode;
import be.devlooj.codaparser.values.Message;
import be.devlooj.codaparser.values.SepaDirectDebit;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TransactionParser {

    public Transaction parse(List<LineInterface> lines) throws Exception {
        TransactionPart1Line transactionPart1Line = (TransactionPart1Line) getFirstLineOfType(lines, LineType.TRANSACTION_PART1);

        if (transactionPart1Line == null) {
            throw new Exception("invalid transaction");
        }

        LocalDate valutaDate = transactionPart1Line.getValutaDate().getValue();
        LocalDate transactionDate = transactionPart1Line.getTransactionDate().getValue();
        double amount = transactionPart1Line.getAmount().getValue();
        int statementSequence = transactionPart1Line.getStatementSequenceNumber().getValue();
        int transactionSequence = transactionPart1Line.getSequenceNumber().getValue();
        int transactionSequenceDetail = transactionPart1Line.getSequenceNumberDetail().getValue();

        SepaDirectDebit sepaDirectDebit = null;
        if (transactionPart1Line.getMessageOrStructuredMessage().getStructuredMessage() != null) {
            sepaDirectDebit = transactionPart1Line.getMessageOrStructuredMessage().getStructuredMessage().getSepaDirectDebit();
        }

        TransactionCode transactionCode = TransactionCode.of(transactionPart1Line.getTransactionCode());
        InformationPart1Line informationPart1Line = (InformationPart1Line) getFirstLineOfType(lines, LineType.INFORMATION_PART1);

        String structuredMessage = "";
        if (transactionPart1Line.getMessageOrStructuredMessage().getStructuredMessage() != null &&
                !transactionPart1Line.getMessageOrStructuredMessage().getStructuredMessage().getStructuredMessage().isEmpty()) {
            structuredMessage = transactionPart1Line.getMessageOrStructuredMessage().getStructuredMessage().getStructuredMessage();
        } else if (informationPart1Line != null &&
                informationPart1Line.getMessageOrStructuredMessage().getStructuredMessage() != null &&
                !informationPart1Line.getMessageOrStructuredMessage().getStructuredMessage().getStructuredMessage().isEmpty()) {
            structuredMessage = informationPart1Line.getMessageOrStructuredMessage().getStructuredMessage().getStructuredMessage();
        }

        List<LineInterface> linesWithAccountInfo = filterLinesOfTypes(
                lines,
                List.of(LineType.TRANSACTION_PART2, LineType.TRANSACTION_PART3)
        );

        AccountOtherPartyParser accountOtherPartyParser = new AccountOtherPartyParser();
        AccountOtherParty account = accountOtherPartyParser.parse(linesWithAccountInfo);

        String message = constructMessage(lines);

        TransactionPart2Line transactionPart2Line = (TransactionPart2Line) getFirstLineOfType(lines, LineType.TRANSACTION_PART2);
        String clientReference = "";
        if (transactionPart2Line != null && !transactionPart2Line.getClientReference().getValue().isEmpty()) {
            clientReference = transactionPart2Line.getClientReference().getValue();
        }

        return new Transaction(
                account,
                statementSequence,
                transactionSequence,
                transactionSequenceDetail,
                transactionDate,
                valutaDate,
                amount,
                message,
                structuredMessage,
                sepaDirectDebit,
                transactionCode,
                clientReference
        );
    }

    public List<List<LineInterface>> filter(List<List<LineInterface>> groupedTransactionLines) {
        List<List<LineInterface>> filteredTransactionLines = new ArrayList<>();
        LineType transactionPart1LineType = LineType.TRANSACTION_PART1;

        for (int idx = 0; idx < groupedTransactionLines.size(); idx++) {
            List<LineInterface> transactionLines = groupedTransactionLines.get(idx);
            TransactionPart1Line transactionPart1Line = (TransactionPart1Line) getFirstLineOfType(transactionLines, transactionPart1LineType);

            if (transactionPart1Line == null) {
                continue;
            }

            if ((transactionPart1Line.getTransactionCode().getOperation().getValue().equals("07") ||
                    transactionPart1Line.getTransactionCode().getType().getValue().equals("1") ||
                    transactionPart1Line.getTransactionCode().getType().getValue().equals("2")) &&
                    transactionPart1Line.getGlobalizationCode().getValue() > 0) {

                TransactionPart1Line nextTransactionPart1Line = null;
                for (int t = idx + 1; t < groupedTransactionLines.size(); t++) {
                    nextTransactionPart1Line = (TransactionPart1Line) getFirstLineOfType(groupedTransactionLines.get(t), transactionPart1LineType);
                    if (nextTransactionPart1Line != null) {
                        break;
                    }
                }

                if (nextTransactionPart1Line != null &&
                        (nextTransactionPart1Line.getTransactionCode().getOperation().getValue().equals("07") ||
                                nextTransactionPart1Line.getTransactionCode().getType().getValue().equals("5") ||
                                nextTransactionPart1Line.getTransactionCode().getType().getValue().equals("6") ||
                                nextTransactionPart1Line.getTransactionCode().getType().getValue().equals("7")) &&
                        nextTransactionPart1Line.getGlobalizationCode().getValue() < transactionPart1Line.getGlobalizationCode().getValue()) {

                    continue;
                }
            }

            filteredTransactionLines.add(transactionLines);
        }

        return filteredTransactionLines;
    }

    private String constructMessage(List<LineInterface> lines) {
        List<LineInterface> transactionLines = filterLinesOfTypes(
                lines,
                List.of(LineType.TRANSACTION_PART1, LineType.TRANSACTION_PART2, LineType.TRANSACTION_PART3)
        );

        StringBuilder message = new StringBuilder();
        for (LineInterface line : transactionLines) {
            Message msg = null;
            if (line instanceof TransactionPart1Line) {
                msg = ((TransactionPart1Line) line).getMessageOrStructuredMessage().getMessage();
            } else if (line instanceof TransactionPart2Line) {
                msg = ((TransactionPart2Line) line).getMessage();
            } else if (line instanceof TransactionPart3Line) {
                msg = ((TransactionPart3Line) line).getMessage();
            }
            if (msg != null) {
                message.append(msg.getValue());
            }
        }

        if (message.isEmpty()) {
            TransactionPart2Line transactionLine = (TransactionPart2Line) getFirstLineOfType(lines, LineType.TRANSACTION_PART2);
            if (transactionLine != null && !transactionLine.getClientReference().getValue().isEmpty()) {
                message.append(transactionLine.getClientReference().getValue());
            }

            List<LineInterface> informationLines = filterLinesOfTypes(
                    lines,
                    List.of(LineType.INFORMATION_PART1, LineType.INFORMATION_PART2, LineType.INFORMATION_PART3)
            );

            if (!message.isEmpty()) {
                message.append(" ");
            }
            for (LineInterface line : informationLines) {
                Message msg = null;
                if (line instanceof InformationPart1Line) {
                    msg = ((InformationPart1Line) line).getMessageOrStructuredMessage().getMessage();
                } else if (line instanceof InformationPart2Line) {
                    msg = ((InformationPart2Line) line).getMessage();
                } else if (line instanceof InformationPart3Line) {
                    msg = ((InformationPart3Line) line).getMessage();
                }
                if (msg != null) {
                    message.append(msg.getValue());
                }
            }
        }

        return message.toString().trim();
    }

    private LineInterface getFirstLineOfType(List<LineInterface> lines, LineType type) {
        for (LineInterface line : lines) {
            if (line.getType().equals(type)) {
                return line;
            }
        }
        return null;
    }

    private List<LineInterface> filterLinesOfTypes(List<LineInterface> lines, List<LineType> types) {
        List<LineInterface> filteredLines = new ArrayList<>();
        for (LineInterface line : lines) {
            if (types.contains(line.getType())) {
                filteredLines.add(line);
            }
        }
        return filteredLines;
    }
}