package org.banksystem.banksystemdemo.api;

import org.banksystem.banksystemdemo.data.BankSystemDatabase;
import org.banksystem.banksystemdemo.dto.ApplyForLoanDto;
import org.banksystem.banksystemdemo.entities.BankAccount;
import org.banksystem.banksystemdemo.entities.Customer;
import org.banksystem.banksystemdemo.entities.Loan;
import org.banksystem.banksystemdemo.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loan")
@CrossOrigin("*")
public class LoanController {
    private final BankSystemDatabase db;

    @Autowired
    public LoanController(BankSystemDatabase database) {
        this.db = database;
    }

    @GetMapping("getLoans/{customerId}")
    public List<Loan> getLoans(@PathVariable String customerId) {
        return db.loans.findBy("customerId", customerId);
    }

    @PostMapping("/apply/{customerId}")
    public void applyForLoan(@PathVariable String customerId, @RequestBody ApplyForLoanDto applyForLoanDto) {

        Customer customer = db.customers.findById(customerId);

        Loan loan = Mapper.loanDtoToLoan(applyForLoanDto);
        loan.setCustomerId(customer.getId());
        calculateLoanPayment(loan);

        BankAccount bankAccount = db.accounts.findFirstBy("customerId", customerId);
        bankAccount.setBalance( bankAccount.getBalance() + applyForLoanDto.loanAmount );

        db.loans.save(loan);
        db.accounts.save(bankAccount);
    }

    private void calculateLoanPayment(Loan loan) {
        double interestByYear = loan.getLoanAmount() * (loan.getInterestRate() / 100.0);
        double totalInterest = interestByYear * loan.getLoanDuration();
        loan.setTotalLoanAmount(((long)totalInterest) + loan.getLoanAmount());
        loan.setYearlyPayment(loan.getTotalLoanAmount() / loan.getLoanDuration() * 1.0);
        loan.setMonthlyPayment(loan.getYearlyPayment() / 12.0);
    }

}
