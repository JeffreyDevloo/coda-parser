package be.devlooj.codaparser.lineparsers;

import be.devlooj.codaparser.lines.InformationPart2Line;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InformationPart2LineParserTest {

    @Test
    void testSample1() {
        InformationPart2LineParser parser = new InformationPart2LineParser();

        String sample = "3200010001MAIN STREET 928                    5480 SOME CITY                                                                  0 0";

        assertTrue(parser.canAcceptString(sample));

        InformationPart2Line result = parser.parse(sample);

        assertEquals(1, result.getSequenceNumber().getValue());
        assertEquals(1, result.getSequenceNumberDetail().getValue());
        assertEquals("MAIN STREET 928                    5480 SOME CITY ", result.getMessage().getValue());
    }
}