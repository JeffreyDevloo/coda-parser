package be.devlooj.codaparser;

import be.devlooj.codaparser.exceptions.InvalidValueException;

import java.util.List;

public class Helpers {

    public static String trimSpace(String string) {
        string = string.replaceAll("^ +", " ");
        string = string.replaceAll(" +$", " ");
        if (string.equals(" ")) {
            string = "";
        }
        return string;
    }

    public static String getTrimmedData(String data, int startPosition) {
        return data.substring(startPosition).trim();
    }

    public static String getTrimmedData(String data, int startPosition, int length) {
        return data.substring(startPosition, startPosition + length).trim();
    }

    public static String formatDateString(String dateCoda) {
        return "20" + dateCoda.substring(4, 6) + "-" + dateCoda.substring(2, 4) + "-" + dateCoda.substring(0, 2);
    }

    public static void validateStringLength(String value, int expectedLength, String typeName) {
        if (value.length() != expectedLength) {
            throw new InvalidValueException(typeName, value, "Should be " + expectedLength + " long");
        }
    }

    public static void validateStringMultipleLengths(String value, List<Integer> expectedLengthArray, String typeName) {
        boolean hasLength = expectedLengthArray.stream().anyMatch(length -> value.length() == length);
        if (!hasLength) {
            throw new InvalidValueException(typeName, value, "Length not valid");
        }
    }

    public static void validateStringDigitOnly(String value, String typeName) {
        if (!value.chars().allMatch(Character::isDigit)) {
            throw new InvalidValueException(typeName, value, "Should contain only numeric values");
        }
    }
}