package org.banksystem.banksystemdemo.entities;

import com.decentraldbcluster.dbclient.odm.annotations.ForeignKey;
import com.decentraldbcluster.dbclient.odm.annotations.Id;
import com.decentraldbcluster.dbclient.odm.annotations.Indexed;
import org.banksystem.banksystemdemo.enums.AccountType;

import java.time.LocalDateTime;

public class BankAccount {
    @Id
    private String accountId;
    @ForeignKey
    @Indexed
    private String userId;
    @ForeignKey
    @Indexed
    private String customerId;
    private AccountType accountType = AccountType.CHECKING;
    private double balance = 0;
    private final LocalDateTime dateOpened = LocalDateTime.now();


    public String getAccountId() {
        return accountId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public LocalDateTime getDateOpened() {
        return dateOpened;
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "accountId='" + accountId + '\'' +
                ", userId='" + userId + '\'' +
                ", customerId='" + customerId + '\'' +
                ", accountType=" + accountType +
                ", balance=" + balance +
                ", dateOpened=" + dateOpened +
                '}';
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }
}
