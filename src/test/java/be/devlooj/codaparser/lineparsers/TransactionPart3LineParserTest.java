package be.devlooj.codaparser.lineparsers;

import be.devlooj.codaparser.lines.TransactionPart3Line;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TransactionPart3LineParserTest {

    @Test
    void testSample1() {
        TransactionPart3LineParser parser = new TransactionPart3LineParser();

        String sample = "2300010000BE54805480215856                  EURBVBA.BAKKER PIET                         MESSAGE                              0 1";

        assertTrue(parser.canAcceptString(sample));

        TransactionPart3Line result = parser.parse(sample);

        assertEquals(1, result.getSequenceNumber().getValue());
        assertEquals(0, result.getSequenceNumberDetail().getValue());
        assertEquals("BE54805480215856                  EUR", result.getOtherAccountNumberAndCurrency().getValue());
        assertEquals("BVBA.BAKKER PIET", result.getOtherAccountName().getValue());
        assertEquals(" MESSAGE ", result.getMessage().getValue());
    }
}