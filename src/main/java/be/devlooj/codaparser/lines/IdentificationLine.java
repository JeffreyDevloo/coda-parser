package be.devlooj.codaparser.lines;

import be.devlooj.codaparser.values.*;

public class IdentificationLine implements LineInterface {
    private final Date creationDate;
    private final BankIdentificationNumber bankIdentificationNumber;
    private final boolean isDuplicate;
    private final ApplicationCode applicationCode;
    private final FileReference fileReference;
    private final AccountName accountName;
    private final Bic accountBic;
    private final CompanyIdentificationNumber accountCompanyIdentificationNumber;
    private final ExternalApplicationCode externalApplicationCode;
    private final TransactionReference transactionReference;
    private final RelatedReference relatedReference;
    private final VersionCode versionCode;

    public IdentificationLine(
            Date creationDate,
            BankIdentificationNumber bankIdentificationNumber,
            boolean isDuplicate,
            ApplicationCode applicationCode,
            FileReference fileReference,
            AccountName accountName,
            Bic accountBic,
            CompanyIdentificationNumber accountCompanyIdentificationNumber,
            ExternalApplicationCode externalApplicationCode,
            TransactionReference transactionReference,
            RelatedReference relatedReference,
            VersionCode versionCode
    ) {
        this.creationDate = creationDate;
        this.bankIdentificationNumber = bankIdentificationNumber;
        this.isDuplicate = isDuplicate;
        this.applicationCode = applicationCode;
        this.fileReference = fileReference;
        this.accountName = accountName;
        this.accountBic = accountBic;
        this.accountCompanyIdentificationNumber = accountCompanyIdentificationNumber;
        this.externalApplicationCode = externalApplicationCode;
        this.transactionReference = transactionReference;
        this.relatedReference = relatedReference;
        this.versionCode = versionCode;
    }

    public LineType getType() {
        return LineType.IDENTIFICATION;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public BankIdentificationNumber getBankIdentificationNumber() {
        return bankIdentificationNumber;
    }

    public boolean isDuplicate() {
        return isDuplicate;
    }

    public ApplicationCode getApplicationCode() {
        return applicationCode;
    }

    public FileReference getFileReference() {
        return fileReference;
    }

    public AccountName getAccountName() {
        return accountName;
    }

    public Bic getAccountBic() {
        return accountBic;
    }

    public CompanyIdentificationNumber getAccountCompanyIdentificationNumber() {
        return accountCompanyIdentificationNumber;
    }

    public ExternalApplicationCode getExternalApplicationCode() {
        return externalApplicationCode;
    }

    public TransactionReference getTransactionReference() {
        return transactionReference;
    }

    public RelatedReference getRelatedReference() {
        return relatedReference;
    }

    public VersionCode getVersionCode() {
        return versionCode;
    }
}