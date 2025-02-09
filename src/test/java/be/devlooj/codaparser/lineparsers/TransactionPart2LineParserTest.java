package be.devlooj.codaparser.lineparsers;

import be.devlooj.codaparser.lines.TransactionPart2Line;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TransactionPart2LineParserTest {

    @Test
    void testSample1() {
        TransactionPart2LineParser parser = new TransactionPart2LineParser();

        String sample = "2200010000  ANOTHER MESSAGE                                           54875                       GEBCEEBB                   1 0";

        assertTrue(parser.canAcceptString(sample));

        TransactionPart2Line result = parser.parse(sample);

        assertEquals(1, result.getSequenceNumber().getValue());
        assertEquals(0, result.getSequenceNumberDetail().getValue());
        assertEquals(" ANOTHER MESSAGE ", result.getMessage().getValue());
        assertEquals("54875", result.getClientReference().getValue());
        assertEquals("GEBCEEBB", result.getOtherAccountBic().getValue());
        assertEquals("", result.getCategoryPurpose().getValue());
        assertEquals("", result.getPurpose().getValue());
    }
}