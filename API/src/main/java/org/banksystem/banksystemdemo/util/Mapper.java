package org.banksystem.banksystemdemo.util;

import org.banksystem.banksystemdemo.dto.BankAccountDto;
import org.banksystem.banksystemdemo.dto.CustomerDto;
import org.banksystem.banksystemdemo.dto.ApplyForLoanDto;
import org.banksystem.banksystemdemo.dto.RegisterDto;
import org.banksystem.banksystemdemo.entities.AppUser;
import org.banksystem.banksystemdemo.entities.BankAccount;
import org.banksystem.banksystemdemo.entities.Customer;
import org.banksystem.banksystemdemo.entities.Loan;

public class Mapper {
    public static Customer mapRegisterDtoToCustomer(final RegisterDto registerDto) {
        Customer customer = new Customer();
        customer.setFirstName(registerDto.firstName);
        customer.setLastName(registerDto.lastName);
        customer.setDateOfBirth(registerDto.dateOfBirth);
        customer.setEmail(registerDto.email);
        customer.setPhoneNumber(registerDto.phoneNumber);
        customer.setAddress(registerDto.address);
        return customer;
    }

    public static AppUser mapRegisterDtoToAppUser(final RegisterDto registerDto) {
        AppUser appUser = new AppUser();
        appUser.setUsername(registerDto.username);
        appUser.setPassword(registerDto.password);
        return appUser;
    }


    public static Loan loanDtoToLoan(final ApplyForLoanDto loanDto) {
        Loan loan = new Loan();
        loan.setLoanAmount(loanDto.loanAmount);
        loan.setLoanType(loanDto.loanType);
        loan.setInterestRate(6);
        loan.setLoanDuration(loanDto.loanDuration);
        return loan;
    }

    public static BankAccountDto mapBankAccountToBankAccountDto(final BankAccount account) {
        BankAccountDto bankAccountDto = new BankAccountDto();
        bankAccountDto.accountId = account.getAccountId();
        bankAccountDto.accountType = account.getAccountType();
        bankAccountDto.dateOpened = account.getDateOpened();
        return bankAccountDto;
    }

    public static CustomerDto mapCustomerToCustomerDto(Customer customer) {
        CustomerDto customerDto = new CustomerDto();
        customerDto.customerId = customer.getId();
        customerDto.address = customer.getAddress();
        customerDto.email = customer.getEmail();
        customerDto.dateOfBirth = customer.getDateOfBirth();
        customerDto.firstName = customer.getFirstName();
        customerDto.lastName = customer.getLastName();
        customerDto.phoneNumber = customer.getPhoneNumber();
        return customerDto;
    }


}
