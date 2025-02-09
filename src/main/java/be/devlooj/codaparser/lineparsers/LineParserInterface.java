package be.devlooj.codaparser.lineparsers;

import be.devlooj.codaparser.lines.LineInterface;

public interface LineParserInterface {
    /**
     * Parse the given string containing into a more readable object
     *
     * @param codaLine the line to parse
     * @return LineParserInterface
     */
    LineInterface parse(String codaLine);

    /**
     * Check if the parser takes into account this type of line
     *
     * @param codaLine the line to check
     * @return boolean
     */
    boolean canAcceptString(String codaLine);
}