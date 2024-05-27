package org.banksystem.banksystemdemo.api;

import org.banksystem.banksystemdemo.data.BankSystemDatabase;
import org.banksystem.banksystemdemo.dto.TransferDto;
import org.banksystem.banksystemdemo.entities.AppNotification;
import org.banksystem.banksystemdemo.entities.BankAccount;
import org.banksystem.banksystemdemo.entities.Transaction;
import org.banksystem.banksystemdemo.enums.TransactionType;
import org.banksystem.banksystemdemo.error.ApiError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transaction")
@CrossOrigin("*")
public class TransactionController {

    private final BankSystemDatabase db;

    @Autowired
    public TransactionController(BankSystemDatabase database) {
        this.db = database;
    }

    @PostMapping("/withdraw/{accountId}")
    public ResponseEntity<?> withdraw(@PathVariable String accountId, @RequestParam double amount) {
        BankAccount bankAccount = db.accounts.findById(accountId);
        if (bankAccount.getBalance() < amount) {
            return ResponseEntity.status(400).body(new ApiError("No sufficient balance"));
        }
        bankAccount.setBalance(bankAccount.getBalance() - amount);
        db.accounts.save(bankAccount);
        db.transaction.save(new Transaction(accountId, TransactionType.WITHDRAW, amount));
        return ResponseEntity.ok().build();
    }


    @PostMapping("/deposit/{accountId}")
    public void deposit(@PathVariable String accountId, @RequestParam double amount) {
        BankAccount bankAccount = db.accounts.findById(accountId);
        bankAccount.setBalance(bankAccount.getBalance() + amount);
        db.accounts.save(bankAccount);
        db.transaction.save(new Transaction(accountId, TransactionType.DEPOSIT, amount));
    }


    @PostMapping("/transfer/{accountId}")
    public ResponseEntity<?> transfer(@PathVariable String accountId, @RequestBody TransferDto transferDto) {
        BankAccount currentAccount = db.accounts.findById(accountId);
        if (currentAccount.getBalance() < transferDto.transferAmount) {
            return ResponseEntity.status(400).body(new ApiError("No sufficient balance"));
        }

        BankAccount destinationAccount = db.accounts.findById(transferDto.destinationAccountId);
        if (destinationAccount == null) {
            return ResponseEntity.status(404).body(new ApiError("Destination user not exists"));
        }

        currentAccount.setBalance(currentAccount.getBalance() - transferDto.transferAmount);
        destinationAccount.setBalance(destinationAccount.getBalance() + transferDto.transferAmount);

        db.accounts.save(currentAccount);
        db.accounts.save(destinationAccount);
        db.transaction.save(new Transaction(accountId, transferDto.destinationAccountId, transferDto.transferAmount));
        db.notifications.save(new AppNotification(transferDto.destinationAccountId, "You received " + transferDto.transferAmount + " from " + accountId));

        return ResponseEntity.ok().build();
    }


    @GetMapping("/getTransactions/{accountId}")
    public List<Transaction> getAccountTransactions(@PathVariable String accountId) {
        List<Transaction> sentTransactions = db.transaction.findBy("accountId", accountId);
        List<Transaction> receivedTransactions = db.transaction.findBy("destinationAccountId", accountId);
        sentTransactions.addAll(receivedTransactions);
        return sentTransactions;
    }
}
