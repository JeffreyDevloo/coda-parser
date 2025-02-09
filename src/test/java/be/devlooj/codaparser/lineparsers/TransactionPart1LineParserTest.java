package be.devlooj.codaparser.lineparsers;

import be.devlooj.codaparser.lines.TransactionPart1Line;
import be.devlooj.codaparser.values.SepaDirectDebit;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class TransactionPart1LineParserTest {

    @Test
    void testSample1() {
        TransactionPart1LineParser parser = new TransactionPart1LineParser();

        String sample = "21000100000001200002835        0000000001767820251214001120000112/4554/46812   813                                 25121421401 0";

        assertTrue(parser.canAcceptString(sample));

        TransactionPart1Line result = parser.parse(sample);

        assertEquals(1, result.getSequenceNumber().getValue());
        assertEquals(0, result.getSequenceNumberDetail().getValue());
        assertEquals("0001200002835", result.getBankReference().getValue());
        assertEquals(1767.820, result.getAmount().getValue());
        assertEquals(LocalDate.of(2014, 12, 25), result.getValutaDate().getValue());
        assertEquals("0", result.getTransactionCode().getType().getValue());
        assertEquals("01", result.getTransactionCode().getFamily().getValue());
        assertEquals("12", result.getTransactionCode().getOperation().getValue());
        assertEquals("000", result.getTransactionCode().getCategory().getValue());
        assertEquals("112/4554/46812   813 ", result.getMessageOrStructuredMessage().getMessage().getValue());
        assertNull(result.getMessageOrStructuredMessage().getStructuredMessage());
        assertEquals(LocalDate.of(2014, 12, 25), result.getTransactionDate().getValue());
        assertEquals(214, result.getStatementSequenceNumber().getValue());
        assertEquals(0, result.getGlobalizationCode().getValue());
    }

    @Test
    void testSampleWithStructuredMessage() {
        TransactionPart1LineParser parser = new TransactionPart1LineParser();

        String sample = "21000100000001200002835        0000000002767820251214001120001101112455446812  813                                 25121421401 0";

        assertTrue(parser.canAcceptString(sample));

        TransactionPart1Line result = parser.parse(sample);

        assertEquals(1, result.getSequenceNumber().getValue());
        assertEquals(0, result.getSequenceNumberDetail().getValue());
        assertEquals("0001200002835", result.getBankReference().getValue());
        assertEquals(2767.820, result.getAmount().getValue());
        assertEquals(LocalDate.of(2014, 12, 25), result.getValutaDate().getValue());
        assertEquals("0", result.getTransactionCode().getType().getValue());
        assertEquals("01", result.getTransactionCode().getFamily().getValue());
        assertEquals("12", result.getTransactionCode().getOperation().getValue());
        assertEquals("000", result.getTransactionCode().getCategory().getValue());
        assertNull(result.getMessageOrStructuredMessage().getMessage());
        assertNotNull(result.getMessageOrStructuredMessage().getStructuredMessage());
        assertEquals("101", result.getMessageOrStructuredMessage().getStructuredMessage().getType());
        assertEquals("112455446812  813                                 ", result.getMessageOrStructuredMessage().getStructuredMessage().getAll());
        assertEquals("112455446812", result.getMessageOrStructuredMessage().getStructuredMessage().getStructuredMessage());
        assertEquals(LocalDate.of(2014, 12, 25), result.getTransactionDate().getValue());
        assertEquals(214, result.getStatementSequenceNumber().getValue());
        assertEquals(0, result.getGlobalizationCode().getValue());
    }

    @Test
    void testSepaDirectDebit() {
        TransactionPart1LineParser parser = new TransactionPart1LineParser();

        String sample = "2100280000VAAS00026BSDDXXXXXXXX1000000000050000050815005030001127050815112BEA123XXXXXXXXXXX                  M123  25121421401 0";

        assertTrue(parser.canAcceptString(sample));

        TransactionPart1Line result = parser.parse(sample);

        assertEquals(28, result.getSequenceNumber().getValue());
        assertEquals(0, result.getSequenceNumberDetail().getValue());
        assertEquals("VAAS00026BSDDXXXXXXXX", result.getBankReference().getValue());
        assertEquals(-50, result.getAmount().getValue());
        assertEquals(LocalDate.of(2015, 8, 5), result.getValutaDate().getValue());
        assertEquals("0", result.getTransactionCode().getType().getValue());
        assertEquals("05", result.getTransactionCode().getFamily().getValue());
        assertEquals("03", result.getTransactionCode().getOperation().getValue());
        assertEquals("000", result.getTransactionCode().getCategory().getValue());
        assertNull(result.getMessageOrStructuredMessage().getMessage());
        assertNotNull(result.getMessageOrStructuredMessage().getStructuredMessage());
        assertEquals("127", result.getMessageOrStructuredMessage().getStructuredMessage().getType());
        assertEquals("050815112BEA123XXXXXXXXXXX                  M123  ", result.getMessageOrStructuredMessage().getStructuredMessage().getAll());
        assertEquals("", result.getMessageOrStructuredMessage().getStructuredMessage().getStructuredMessage());
        assertEquals(LocalDate.of(2014, 12, 25), result.getTransactionDate().getValue());
        assertEquals(214, result.getStatementSequenceNumber().getValue());
        assertEquals(0, result.getGlobalizationCode().getValue());

        SepaDirectDebit sepaDirectDebit = result.getMessageOrStructuredMessage().getStructuredMessage().getSepaDirectDebit();
        assertNotNull(sepaDirectDebit);
        assertEquals(LocalDate.of(2015, 8, 5), sepaDirectDebit.getSettlementDate().getValue());
        assertEquals(1, sepaDirectDebit.getType());
        assertEquals(1, sepaDirectDebit.getScheme());
        assertEquals(2, sepaDirectDebit.getPaidReason());
        assertEquals("BEA123XXXXXXXXXXX", sepaDirectDebit.getCreditorIdentificationCode());
        assertEquals("M123", sepaDirectDebit.getMandateReference());
    }
}