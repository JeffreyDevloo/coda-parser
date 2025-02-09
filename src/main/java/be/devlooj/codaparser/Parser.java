package be.devlooj.codaparser;

import be.devlooj.codaparser.lines.LineInterface;
import be.devlooj.codaparser.lines.LineType;
import be.devlooj.codaparser.statements.Statement;
import be.devlooj.codaparser.statementsparsers.StatementParser;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Parser implements ParserInterface<Statement> {

    private final LinesParser linesParser;

    public Parser() {
        this.linesParser = new LinesParser();
    }

    @Override
    public List<Statement> parseFile(File codaFile) throws Exception {
        List<LineInterface> lines = linesParser.parseFile(codaFile);
        return convertToStatements(lines);
    }

    @Override
    public List<Statement> parse(List<String> codaLines) throws Exception {
        List<LineInterface> lines = linesParser.parse(codaLines);
        return convertToStatements(lines);
    }

    private List<Statement> convertToStatements(List<LineInterface> lines) throws Exception {
        List<List<LineInterface>> linesGroupedPerStatement = groupTransactionsPerStatement(lines);

        List<Statement> statements = new ArrayList<>();
        StatementParser parser = new StatementParser();
        for (List<LineInterface> linesForStatement : linesGroupedPerStatement) {
            Statement statement = parser.parse(linesForStatement);
            statements.add(statement);
        }

        return statements;
    }

    public List<List<LineInterface>> groupTransactionsPerStatement(List<LineInterface> lines) {
        List<List<LineInterface>> statements = new ArrayList<>();
        int idx = -1;

        for (LineInterface line : lines) {
            if (statements.isEmpty() || line.getType() == LineType.IDENTIFICATION) {
                idx += 1;
                statements.add(new ArrayList<>());
            }

            statements.get(idx).add(line);
        }

        return statements;
    }
}