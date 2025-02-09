package be.devlooj.codaparser.lineparsers;

import be.devlooj.codaparser.lines.EndSummaryLine;
import be.devlooj.codaparser.values.Amount;

public class EndSummaryLineParser implements LineParserInterface {

    @Override
    public EndSummaryLine parse(String codaLine) {
        return new EndSummaryLine(
                new Amount(codaLine.substring(22, 37)), // taken from the account (=debetomzet)
                new Amount(codaLine.substring(37, 52))  // added to the account (=creditomzet)
        );
    }

    @Override
    public boolean canAcceptString(String codaLine) {
        return codaLine.length() == 128 && codaLine.startsWith("9");
    }
}
