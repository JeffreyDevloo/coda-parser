package be.devlooj.codaparser.lineparsers;

import be.devlooj.codaparser.lines.IdentificationLine;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class IdentificationLineParserTest {

    @Test
    void testSample1() {
        IdentificationLineParser parser = new IdentificationLineParser();

        String sample = "0000018011520105        0938409934CODELICIOUS               GEBABEBB   09029308273 00001          984309          834080       2";

        assertTrue(parser.canAcceptString(sample));

        IdentificationLine result = parser.parse(sample);

        assertEquals(LocalDate.of(2015, 1, 18), result.getCreationDate().getValue());
        assertEquals("201", result.getBankIdentificationNumber().getValue());
        assertEquals("05", result.getApplicationCode().getValue());
        assertFalse(result.isDuplicate());
        assertEquals("0938409934", result.getFileReference().getValue());
        assertEquals("CODELICIOUS", result.getAccountName().getValue());
        assertEquals("GEBABEBB", result.getAccountBic().getValue());
        assertEquals("09029308273", result.getAccountCompanyIdentificationNumber().getValue());
        assertEquals("00001", result.getExternalApplicationCode().getValue());
        assertEquals("984309", result.getTransactionReference().getValue());
        assertEquals("834080", result.getRelatedReference().getValue());
        assertEquals("2", result.getVersionCode().getValue());
    }
}