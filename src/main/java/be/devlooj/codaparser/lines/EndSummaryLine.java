package be.devlooj.codaparser.lines;


import be.devlooj.codaparser.values.Amount;

public class EndSummaryLine implements LineInterface {
    private final Amount debetAmount;
    private final Amount creditAmount;

    public EndSummaryLine(Amount debetAmount, Amount creditAmount) {
        this.debetAmount = debetAmount;
        this.creditAmount = creditAmount;
    }

    @Override
    public LineType getType() {
        return LineType.END_SUMMARY;
    }

    public Amount getDebetAmount() {
        return debetAmount;
    }

    public Amount getCreditAmount() {
        return creditAmount;
    }
}