package org.banksystem.banksystemdemo.dto;

import org.banksystem.banksystemdemo.enums.AccountType;

import java.time.LocalDateTime;

public class BankAccountDto {
    public String accountId;
    public AccountType accountType;
    public LocalDateTime dateOpened;
}
