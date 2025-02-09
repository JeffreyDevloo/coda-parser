package be.devlooj.codaparser.lines;

public enum LineType {
    UNKNOWN(-1),
    IDENTIFICATION(0),
    INITIAL_STATE(10),
    TRANSACTION_PART1(21),
    TRANSACTION_PART2(22),
    TRANSACTION_PART3(23),
    INFORMATION_PART1(31),
    INFORMATION_PART2(32),
    INFORMATION_PART3(33),
    MESSAGE(40),
    NEW_STATE(80),
    END_SUMMARY(90);

    private final int value;

    LineType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}