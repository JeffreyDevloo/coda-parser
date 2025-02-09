package be.devlooj.codaparser;

import be.devlooj.codaparser.lines.LineInterface;
import org.junit.jupiter.api.Test;

import java.util.List;

import static be.devlooj.codaparser.ResourceUtil.readLinesFromResource;
import static org.junit.jupiter.api.Assertions.assertEquals;

class LinesParserTest {

    @Test
    void testSample1() throws Exception {
        LinesParser parser = new LinesParser();

        List<LineInterface> result = parser.parse(getSample("sample5.cod"));

        assertEquals(16, result.size());
    }

    public static List<String> getSample(String sampleFile) {
        return readLinesFromResource(LinesParserTest.class, "Samples/" + sampleFile);
    }
}