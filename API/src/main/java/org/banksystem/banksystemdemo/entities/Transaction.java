package org.banksystem.banksystemdemo.entities;

import com.decentraldbcluster.dbclient.odm.annotations.ForeignKey;
import com.decentraldbcluster.dbclient.odm.annotations.Id;
import com.decentraldbcluster.dbclient.odm.annotations.Indexed;
import org.banksystem.banksystemdemo.enums.TransactionType;

import java.time.LocalDateTime;

public class Transaction {
    @Id
    private String transactionId;
    @ForeignKey
    private String accountId;
    @ForeignKey
    @Indexed
    private String destinationAccountId;
    private TransactionType transactionType;
    private double amount;
    private final LocalDateTime transactionDate = LocalDateTime.now();

    public Transaction() {}

    public Transaction(String accountId, String destinationAccountId, double amount) {
        this.accountId = accountId;
        transactionType = TransactionType.TRANSFER;
        this.destinationAccountId = destinationAccountId;
        this.amount = amount;
    }

    public Transaction(String accountId, TransactionType transactionType, double amount) {
        this.accountId = accountId;
        this.transactionType = transactionType;
        this.amount = amount;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public String getDestinationAccountId() {
        return destinationAccountId;
    }

    public void setDestinationAccountId(String destinationAccountId) {
        this.destinationAccountId = destinationAccountId;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId='" + transactionId + '\'' +
                ", accountId='" + accountId + '\'' +
                ", destinationAccountId='" + destinationAccountId + '\'' +
                ", transactionType=" + transactionType +
                ", amount=" + amount +
                ", transactionDate=" + transactionDate +
                '}';
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }
}
