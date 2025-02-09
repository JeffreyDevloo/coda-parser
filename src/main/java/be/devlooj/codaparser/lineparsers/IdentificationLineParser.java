package be.devlooj.codaparser.lineparsers;

import be.devlooj.codaparser.lines.IdentificationLine;
import be.devlooj.codaparser.values.*;

public class IdentificationLineParser implements LineParserInterface {

    @Override
    public IdentificationLine parse(String codaLine) {
        return new IdentificationLine(
                new Date(codaLine.substring(5, 11)), // Adjusted to match the date format
                new BankIdentificationNumber(codaLine.substring(11, 14)),
                codaLine.charAt(16) == 'D',
                new ApplicationCode(codaLine.substring(14, 16)),
                new FileReference(codaLine.substring(24, 34)),
                new AccountName(codaLine.substring(34, 60)),
                new Bic(codaLine.substring(60, 71)),
                new CompanyIdentificationNumber(codaLine.substring(71, 82)),
                new ExternalApplicationCode(codaLine.substring(83, 88)),
                new TransactionReference(codaLine.substring(88, 104)),
                new RelatedReference(codaLine.substring(104, 120)),
                new VersionCode(codaLine.substring(127, 128))
        );
    }

    @Override
    public boolean canAcceptString(String codaLine) {
        return codaLine.length() == 128 && codaLine.charAt(0) == '0';
    }
}