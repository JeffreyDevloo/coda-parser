package be.devlooj.codaparser;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public interface ParserInterface<T> {
    List<T> parseFile(File codaFile) throws Exception;

    List<T> parse(List<String> codaLines) throws Exception;

    static List<String> fileToCodaLines(File codaFile) throws IOException {
        return Files.readAllLines(codaFile.toPath());
    }
}
