package be.devlooj.codaparser.lineparsers;

import be.devlooj.codaparser.lines.EndSummaryLine;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EndSummaryLineParserTest {

    @Test
    void testSample1() {
        EndSummaryLineParser parser = new EndSummaryLineParser();

        String sample = "9               000015000000016837520000000003967220                                                                           1";

        assertTrue(parser.canAcceptString(sample));

        EndSummaryLine result = parser.parse(sample);

        assertEquals(16837.520, result.getDebetAmount().getValue());
        assertEquals(3967.220, result.getCreditAmount().getValue());
    }
}