package be.devlooj.codaparser.lineparsers;

import be.devlooj.codaparser.lines.InformationPart1Line;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InformationPart1LineParserTest {

    @Test
    void testSample1() {
        InformationPart1LineParser parser = new InformationPart1LineParser();
        String sample = "31000100010007500005482        004800001001BVBA.BAKKER PIET                                                                  1 0";

        assertTrue(parser.canAcceptString(sample));
        InformationPart1Line result = parser.parse(sample);

        assertEquals(1, result.getSequenceNumber().getValue());
        assertEquals(1, result.getSequenceNumberDetail().getValue());
        assertEquals("0007500005482", result.getBankReference().getValue());
        assertEquals("0", result.getTransactionCode().getType().getValue());
        assertEquals("04", result.getTransactionCode().getFamily().getValue());
        assertEquals("80", result.getTransactionCode().getOperation().getValue());
        assertEquals("000", result.getTransactionCode().getCategory().getValue());
        assertNull(result.getMessageOrStructuredMessage().getMessage());
        assertNotNull(result.getMessageOrStructuredMessage().getStructuredMessage());
        assertEquals("001", result.getMessageOrStructuredMessage().getStructuredMessage().getType());
        assertEquals("BVBA.BAKKER PIET                                                      ", result.getMessageOrStructuredMessage().getStructuredMessage().getAll());
        assertTrue(result.getMessageOrStructuredMessage().getStructuredMessage().getStructuredMessage().isEmpty());
    }

    @Test
    void testSampleWithAccents() {
        InformationPart1LineParser parser = new InformationPart1LineParser();
        String sample = "31000100073403076534383000143  335370000ekeningING Plus BE12 3215 1548 2121 EUR Compte à vue BE25 3215 2158 2315             0 1";

        assertTrue(parser.canAcceptString(sample));
        InformationPart1Line result = parser.parse(sample);

        assertEquals(1, result.getSequenceNumber().getValue());
        assertEquals("ekeningING Plus BE12 3215 1548 2121 EUR Compte à vue BE25 3215 2158 2315 ", result.getMessageOrStructuredMessage().getMessage().getValue());
    }
}