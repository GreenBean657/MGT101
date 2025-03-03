package org.BankingSystem.models;

public class TransactionHistory {
    String transactionID;
    String date;
    float amount;
    TypeOfTransaction type;

    public TransactionHistory(String transactionID, String date, float amount, TypeOfTransaction type) {
        this.transactionID = transactionID;
        this.date = date;
        this.amount = amount;
        this.type = type;
    }

    @Override
    public String toString() {
        return transactionID +
                "   " + date + "\n"
                + amount +
                "\n" + type;
    }
}
