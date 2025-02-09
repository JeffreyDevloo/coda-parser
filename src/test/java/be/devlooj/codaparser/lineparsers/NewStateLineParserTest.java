package be.devlooj.codaparser.lineparsers;

import be.devlooj.codaparser.lines.NewStateLine;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class NewStateLineParserTest {

    @Test
    void testSample1() {
        NewStateLineParser parser = new NewStateLineParser();

        String sample = "8225001548226815 EUR0BE                  1000000500012100120515                                                                0";

        assertTrue(parser.canAcceptString(sample));

        NewStateLine result = parser.parse(sample);

        assertEquals(225, result.getStatementSequenceNumber().getValue());
        assertEquals("001548226815 EUR0BE", result.getAccount().getValue());
        assertEquals(-500012.100, result.getBalance().getValue());
        assertEquals(LocalDate.of(2015, 5, 12), result.getDate().getValue());
    }
}