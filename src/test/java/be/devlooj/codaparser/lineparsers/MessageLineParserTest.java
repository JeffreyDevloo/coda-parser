package be.devlooj.codaparser.lineparsers;

import be.devlooj.codaparser.lines.MessageLine;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MessageLineParserTest {

    @Test
    void testSample1() {
        MessageLineParser parser = new MessageLineParser();

        String sample = "4 00010005                      THIS IS A PUBLIC MESSAGE                                                                       0";

        assertTrue(parser.canAcceptString(sample));

        MessageLine result = parser.parse(sample);

        assertEquals(1, result.getSequenceNumber().getValue());
        assertEquals(5, result.getSequenceNumberDetail().getValue());
        assertEquals("THIS IS A PUBLIC MESSAGE ", result.getContent().getValue());
    }

    @Test
    void testSample2() {
        MessageLineParser parser = new MessageLineParser();

        String sample = "4 00020000                                              ACCOUNT INFORMATION                                                    1";

        assertTrue(parser.canAcceptString(sample));

        MessageLine result = parser.parse(sample);

        assertEquals(2, result.getSequenceNumber().getValue());
        assertEquals(0, result.getSequenceNumberDetail().getValue());
        assertEquals(" ACCOUNT INFORMATION ", result.getContent().getValue());
    }
}