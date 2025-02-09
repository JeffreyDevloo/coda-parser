package be.devlooj.codaparser;

import be.devlooj.codaparser.lineparsers.*;
import be.devlooj.codaparser.lines.LineInterface;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class LinesParser implements ParserInterface<LineInterface> {

    private List<LineParserInterface> lineParsers;

    public LinesParser() {
        initLineParsers();
    }

    @Override
    public List<LineInterface> parseFile(File codaFile) throws Exception {
        return parse(ParserInterface.fileToCodaLines(codaFile));
    }

    @Override
    public List<LineInterface> parse(List<String> codaLines) throws Exception {
        List<LineInterface> list = new ArrayList<>();

        for (String line : codaLines) {
            if (!line.isEmpty()) {
                LineInterface lineObject = null;

                for (LineParserInterface parser : lineParsers) {
                    if (parser.canAcceptString(line)) {
                        lineObject = parser.parse(line);
                        break;
                    }
                }

                if (lineObject == null) {
                    throw new Exception("Could not parse");
                }

                list.add(lineObject);
            }
        }

        if (list.isEmpty()) {
            throw new Exception("No data given");
        }

        return list;
    }

    private void initLineParsers() {
        lineParsers = List.of(
                new IdentificationLineParser(),
                new InitialStateLineParser(),
                new TransactionPart1LineParser(),
                new TransactionPart2LineParser(),
                new TransactionPart3LineParser(),
                new InformationPart1LineParser(),
                new InformationPart2LineParser(),
                new InformationPart3LineParser(),
                new MessageLineParser(),
                new NewStateLineParser(),
                new EndSummaryLineParser()
        );
    }
}