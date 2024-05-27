package org.banksystem.banksystemdemo.api;

import org.banksystem.banksystemdemo.data.BankSystemDatabase;
import org.banksystem.banksystemdemo.dto.BankAccountDto;
import org.banksystem.banksystemdemo.entities.BankAccount;
import org.banksystem.banksystemdemo.util.Mapper;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/account")
@CrossOrigin("*")
public class AccountController {

    private final BankSystemDatabase db;

    public AccountController(BankSystemDatabase db) {
        this.db = db;
    }

    @GetMapping("getBalance/{accountId}")
    public double getAccountBalance(@PathVariable String accountId) {
        BankAccount account = db.accounts.findById(accountId);
        return account.getBalance();
    }

    @GetMapping("getDetails/{accountId}")
    public BankAccountDto getAccountDetails(@PathVariable String accountId) {
        BankAccount account = db.accounts.findById(accountId);
        return Mapper.mapBankAccountToBankAccountDto(account);
    }

}
