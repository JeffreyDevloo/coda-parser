package be.devlooj.codaparser.lineparsers;

import be.devlooj.codaparser.lines.InitialStateLine;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class InitialStateLineParserTest {

    @Test
    void testSample1() {
        InitialStateLineParser parser = new InitialStateLineParser();

        String sample = "10155001548226815 EUR0BE                  0000000004004100241214CODELICIOUS               PROFESSIONAL ACCOUNT               255";

        assertTrue(parser.canAcceptString(sample));

        InitialStateLine result = parser.parse(sample);

        assertEquals("0", result.getAccount().getNumberType().getValue());
        assertEquals(155, result.getStatementSequenceNumber().getValue());
        assertEquals("001548226815", result.getAccount().getNumber().getValue());
        assertEquals("EUR", result.getAccount().getCurrency().getCurrencyCode());
        assertEquals("BE", result.getAccount().getCountry().getCountryCode());
        assertFalse(result.getAccount().getNumber().isIbanNumber());
        assertEquals(4004.100, result.getBalance().getValue());
        assertEquals(LocalDate.of(2014, 12, 24), result.getDate().getValue());
        assertEquals("CODELICIOUS", result.getAccount().getName().getValue());
        assertEquals("PROFESSIONAL ACCOUNT", result.getAccount().getDescription().getValue());
        assertEquals(255, result.getPaperStatementSequenceNumber().getValue());
    }

    @Test
    void testAccountIsIbanIsSetCorrectly() {
        InitialStateLineParser parser = new InitialStateLineParser();

        String sample = "13155001548226815 EUR0BE                  0000000004004100241214CODELICIOUS               PROFESSIONAL ACCOUNT               255";

        assertTrue(parser.canAcceptString(sample));

        InitialStateLine result = parser.parse(sample);

        assertTrue(result.getAccount().getNumber().isIbanNumber());
    }
}