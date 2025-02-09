package be.devlooj.codaparser;

import be.devlooj.codaparser.statements.Statement;
import be.devlooj.codaparser.statements.Transaction;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static be.devlooj.codaparser.ResourceUtil.readLinesFromResource;
import static org.junit.jupiter.api.Assertions.*;

class ParserTest {

    @Test
    void testSample5SimpleFormat() throws Exception {
        Parser parser = new Parser();

        List<Statement> result = parser.parse(getSample("sample5.cod"));

        assertEquals(1, result.size());
        Statement statement = result.getFirst();

        assertNotNull(statement.getDate());
        assertNotNull(statement.getAccount());

        assertEquals(3, statement.getTransactions().size());
        List<Transaction> transactions = statement.getTransactions();
        Transaction transaction1 = transactions.getFirst();
        Transaction transaction2 = transactions.get(1);
        Transaction transaction3 = transactions.get(2);

        assertNotNull(transaction1.getAccount());
        assertNotNull(transaction1.getTransactionDate());
        assertNotNull(transaction1.getValutaDate());
        assertNotNull(transaction1.getMessage());
        assertEquals(1, transaction1.getTransactionSequence());
        assertEquals(214, transaction1.getStatementSequence());

        assertNotNull(transaction2.getAccount());
        assertNotNull(transaction2.getTransactionDate());
        assertNotNull(transaction2.getValutaDate());
        assertNotNull(transaction2.getStructuredMessage());
        assertEquals(2, transaction2.getTransactionSequence());
        assertEquals(214, transaction2.getStatementSequence());

        assertNotNull(transaction3.getAccount());
        assertNotNull(transaction3.getTransactionDate());
        assertNotNull(transaction3.getValutaDate());
        assertNotNull(transaction3.getMessage());
        assertEquals(9, transaction3.getTransactionSequence());
        assertEquals(214, transaction3.getStatementSequence());
    }

    @Test
    void testMessageOnMultipleLinesTransactionBlock() throws Exception {
        Parser parser = new Parser();
        List<Statement> result = parser.parse(getSample("sample3.cod"));

        assertEquals("Message goes here and continues here or here", result.getFirst().getTransactions().getFirst().getMessage());
    }

    @Test
    void testMessageOnMultipleLinesInformationBlock() throws Exception {
        Parser parser = new Parser();
        List<Statement> result = parser.parse(getSample("sample4.cod"));

        assertEquals("Europese overschrijving (zie bijlage)  + 17.233,54Van: COMPANY BLABLABLAH BVBA - BE64NOT PR", result.getFirst().getTransactions().getFirst().getMessage());
    }

    @Test
    void testNoAccount() throws Exception {
        Parser parser = new Parser();
        List<Statement> result = parser.parse(getSample("sample2.cod"));

        assertTrue(result.getFirst().getTransactions().getFirst().getAccount().getName().isEmpty());
        assertEquals("Zichtrekening nr  21354598  - 2,11Justification in annex 00001680", result.getFirst().getTransactions().getFirst().getMessage());
    }

    @Test
    void testHas4EntriesWithStructuredMessage() throws Exception {
        Parser parser = new Parser();
        List<Statement> result = parser.parse(getSample("sample1.cod"));

        assertEquals(1, result.size());
        assertEquals(17752.12, result.getFirst().getInitialBalance());
        assertEquals(17832.12, result.getFirst().getNewBalance());
        assertEquals(LocalDate.of(2017, 10, 11), result.getFirst().getDate());
        assertTrue(result.getFirst().getInformationalMessage().isEmpty());

        assertEquals(4, result.getFirst().getTransactions().size());
        assertEquals("GROTE WEG            32            3215    HASSELT", result.getFirst().getTransactions().getFirst().getMessage());
        assertEquals("000003505158", result.getFirst().getTransactions().getFirst().getStructuredMessage());
        assertEquals(5, result.getFirst().getTransactions().getFirst().getAmount());
        assertEquals("KLANT1 MET NAAM1", result.getFirst().getTransactions().getFirst().getAccount().getName());
        assertEquals("BE22313215646432", result.getFirst().getTransactions().getFirst().getAccount().getNumber());
    }

    @Test
    void testSample6() throws Exception {
        Parser parser = new Parser();

        List<Statement> result = parser.parse(getSample("sample6.cod"));

        assertEquals(1, result.size());
        Statement statement = result.getFirst();

        assertNotNull(statement.getAccount());
        assertEquals(3, statement.getTransactions().size());
        assertEquals(LocalDate.of(2015, 1, 18), statement.getDate());
        assertEquals(4004.1, statement.getInitialBalance());
        assertEquals(-500012.1, statement.getNewBalance());
        assertEquals("THIS IS A PUBLIC MESSAGE", statement.getInformationalMessage());

        assertEquals("CODELICIOUS", statement.getAccount().getName());
        assertEquals("GEBABEBB", statement.getAccount().getBic());
        assertEquals("09029308273", statement.getAccount().getCompanyIdentificationNumber());
        assertEquals("001548226815", statement.getAccount().getNumber());
        assertEquals("EUR", statement.getAccount().getCurrencyCode());
        assertEquals("BE", statement.getAccount().getCountryCode());
        assertEquals("PROFESSIONAL ACCOUNT", statement.getAccount().getDescription());

        Transaction transaction1 = statement.getTransactions().getFirst();
        Transaction transaction2 = statement.getTransactions().get(1);
        Transaction transaction3 = statement.getTransactions().get(2);

        assertNotNull(transaction1.getAccount());
        assertEquals(LocalDate.of(2014, 12, 25), transaction1.getTransactionDate());
        assertEquals(LocalDate.of(2014, 12, 25), transaction1.getValutaDate());
        assertEquals(-767.823, transaction1.getAmount());
        assertEquals("112/4554/46812   813  ANOTHER MESSAGE  MESSAGE", transaction1.getMessage());
        assertTrue(transaction1.getStructuredMessage().isEmpty());

        assertEquals("BVBA.BAKKER PIET", transaction1.getAccount().getName());
        assertEquals("GEBCEEBB", transaction1.getAccount().getBic());
        assertEquals("BE54805480215856", transaction1.getAccount().getNumber());
        assertEquals("EUR", transaction1.getAccount().getCurrencyCode());
        assertEquals(1, transaction1.getTransactionSequence());
        assertEquals(214, transaction1.getStatementSequence());

        assertEquals("54875", transaction2.getMessage());
        assertEquals("112455446812", transaction2.getStructuredMessage());
        assertEquals(2, transaction2.getTransactionSequence());
        assertEquals(214, transaction2.getStatementSequence());

        assertTrue(transaction3.getAccount().getName().isEmpty());
        assertEquals("GEBCEEBB", transaction3.getAccount().getBic());
        assertEquals(9, transaction3.getTransactionSequence());
        assertEquals(214, transaction3.getStatementSequence());
    }

    @Test
    void testGroupedTransactions() throws Exception {
        Parser parser = new Parser();

        List<Statement> result = parser.parse(getSample("sample7.cod"));

        assertEquals(1, result.size());

        assertEquals(17752.12, result.getFirst().getInitialBalance());
        assertEquals(17832.12, result.getFirst().getNewBalance());
        assertEquals(LocalDate.of(2017, 10, 11), result.getFirst().getDate());
        assertTrue(result.getFirst().getInformationalMessage().isEmpty());

        assertEquals(3, result.getFirst().getTransactions().size());
        assertEquals("VOETGANGERSTRAAT 26                1215        ANTWERPEN", result.getFirst().getTransactions().getFirst().getMessage());
        assertEquals("000003515846", result.getFirst().getTransactions().getFirst().getStructuredMessage());
        assertEquals(-25, result.getFirst().getTransactions().getFirst().getAmount());
        assertEquals("KLANT2 NAAM2", result.getFirst().getTransactions().getFirst().getAccount().getName());
        assertEquals("BE25646548413215", result.getFirst().getTransactions().getFirst().getAccount().getNumber());
        assertEquals(1, result.getFirst().getTransactions().getFirst().getTransactionSequence());
        assertEquals(2, result.getFirst().getTransactions().getFirst().getTransactionSequenceDetail());

        assertEquals(1, result.getFirst().getTransactions().get(1).getTransactionSequence());
        assertEquals(3, result.getFirst().getTransactions().get(1).getTransactionSequenceDetail());

        assertEquals(1, result.getFirst().getTransactions().get(2).getTransactionSequence());
        assertEquals(4, result.getFirst().getTransactions().get(2).getTransactionSequenceDetail());
    }

    @Test
    void testOnlyGroupedTransactions() throws Exception {
        Parser parser = new Parser();

        List<Statement> result = parser.parse(getSample("sample8.cod"));

        assertEquals(1, result.size());
        assertEquals(2, result.getFirst().getTransactions().size());
    }

    @Test
    void testClientReference() throws Exception {
        Parser parser = new Parser();

        List<Statement> result = parser.parse(getSample("sample9.cod"));

        assertEquals(1, result.size());
        assertEquals(1, result.getFirst().getTransactions().size());
        assertEquals("243690000141", result.getFirst().getTransactions().getFirst().getClientReference());
    }

    @Test
    void testTotaledTransactionsWithDetails() throws Exception {
        Parser parser = new Parser();

        List<Statement> result = parser.parse(getSample("sample10.cod"));

        assertEquals(1, result.size());

        assertEquals(100, result.getFirst().getInitialBalance());
        assertEquals(1100, result.getFirst().getNewBalance());
        assertEquals(LocalDate.of(2024, 6, 6), result.getFirst().getDate());
        assertTrue(result.getFirst().getInformationalMessage().isEmpty());

        assertEquals(2, result.getFirst().getTransactions().size());
        assertEquals(250, result.getFirst().getTransactions().getFirst().getAmount());
        assertEquals("KLANT1 MET NAAM1", result.getFirst().getTransactions().getFirst().getAccount().getName());
        assertEquals("BE22313215646432", result.getFirst().getTransactions().getFirst().getAccount().getNumber());

        assertEquals(750, result.getFirst().getTransactions().get(1).getAmount());
        assertEquals("KLANT2 MET NAAM2", result.getFirst().getTransactions().get(1).getAccount().getName());
        assertEquals("BE25646548413215", result.getFirst().getTransactions().get(1).getAccount().getNumber());
    }

    @Test
    void testTotaledTransactionsNoDetails() throws Exception {
        Parser parser = new Parser();

        List<Statement> result = parser.parse(getSample("sample11.cod"));

        assertEquals(1, result.size());

        assertEquals(100, result.getFirst().getInitialBalance());
        assertEquals(1100, result.getFirst().getNewBalance());
        assertEquals(LocalDate.of(2024, 6, 6), result.getFirst().getDate());
        assertTrue(result.getFirst().getInformationalMessage().isEmpty());

        assertEquals(1, result.getFirst().getTransactions().size());
        assertEquals(1000, result.getFirst().getTransactions().getFirst().getAmount());
    }

    private static List<String> getSample(String sampleFile) {
        return readLinesFromResource(ParserTest.class, "Samples/" + sampleFile);
    }
}