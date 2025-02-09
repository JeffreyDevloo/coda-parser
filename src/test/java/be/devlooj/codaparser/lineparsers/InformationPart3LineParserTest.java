package be.devlooj.codaparser.lineparsers;

import be.devlooj.codaparser.lines.InformationPart3Line;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InformationPart3LineParserTest {

    @Test
    void testSample1() {
        InformationPart3LineParser parser = new InformationPart3LineParser();

        String sample = "3300010001SOME INFORMATION ABOUT THIS TRANSACTION                                                                            0 0";

        assertTrue(parser.canAcceptString(sample));

        InformationPart3Line result = parser.parse(sample);

        assertEquals(1, result.getSequenceNumber().getValue());
        assertEquals(1, result.getSequenceNumberDetail().getValue());
        assertEquals("SOME INFORMATION ABOUT THIS TRANSACTION ", result.getMessage().getValue());
    }
}