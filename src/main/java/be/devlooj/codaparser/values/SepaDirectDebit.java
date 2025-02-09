package be.devlooj.codaparser.values;

import be.devlooj.codaparser.Helpers;

import java.util.List;

public class SepaDirectDebit {
    private final Date settlementDate;
    private final int type;
    private final int scheme;
    private final int paidReason;
    private final String creditorIdentificationCode;
    private final String mandateReference;

    public SepaDirectDebit(String value) {
        Helpers.validateStringMultipleLengths(value, List.of(50, 70), "SepaDirectDebit");

        this.settlementDate = new Date(value.substring(0, 6));
        this.type = Integer.parseInt(value.substring(6, 7));
        this.scheme = Integer.parseInt(value.substring(7, 8));
        this.paidReason = Integer.parseInt(value.substring(8, 9));
        this.creditorIdentificationCode = Helpers.getTrimmedData(value, 9, 35);
        this.mandateReference = Helpers.getTrimmedData(value, 44);  // 35
    }

    public Date getSettlementDate() {
        return settlementDate;
    }

    public int getType() {
        return type;
    }

    public int getScheme() {
        return scheme;
    }

    public int getPaidReason() {
        return paidReason;
    }

    public String getCreditorIdentificationCode() {
        return creditorIdentificationCode;
    }

    public String getMandateReference() {
        return mandateReference;
    }
}