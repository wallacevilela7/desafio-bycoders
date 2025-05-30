package tech.wvs.desafiobycoders.domain.enums;

public enum TransactionType {
    DEBIT(1, "Debit", "Inbound", "+"),
    BANK_SLIP(2, "Bank slip payment", "Outbound", "-"),
    FINANCING(3, "Financing payment", "Outbound", "-"),
    CREDIT(4, "Credit card receipt", "Inbound", "+"),
    LOAN_RECEIPT(5, "Loan receipt", "Inbound", "+"),
    SALE(6, "Sale", "Inbound", "+"),
    TED_RECEIPT(7, "TED transfer receipt", "Inbound", "+"),
    DOC_RECEIPT(8, "DOC transfer receipt", "Inbound", "+"),
    RENT(9, "Rent payment", "Outbound", "-");

    private final int code;
    private final String description;
    private final String category;
    private final String operationSign;

    TransactionType(int code, String description, String category, String operationSign) {
        this.code = code;
        this.description = description;
        this.category = category;
        this.operationSign = operationSign;
    }

    public static TransactionType getByCode(int code) {
        for (TransactionType type : TransactionType.values()) {
            if (type.getCode() == code) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid transaction type code: " + code);
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public String getOperationSign() {
        return operationSign;
    }
}
