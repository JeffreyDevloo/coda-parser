# coda-parser
Java parser for Belgian CODA banking files.

This parser is a port of the https://github.com/wimverstuyf/php-coda-parser.

## Usage

```java
import be.devlooj.codaparser.Parser;

import java.io.File;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        Parser parser = new Parser();
        List<Statement> result = parser.parseFile(Paths.get("path/to/coda-file.txt").toFile());
    }
}
```
