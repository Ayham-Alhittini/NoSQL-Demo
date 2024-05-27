package org.banksystem.banksystemdemo.dto;

import org.banksystem.banksystemdemo.enums.AccountType;

import java.time.LocalDateTime;

public class RegisterDto {
    public String username;
    public String password;


    public String firstName;
    public String lastName;
    public String email;
    public LocalDateTime dateOfBirth;
    public String phoneNumber;
    public String address;
    public AccountType accountType;
}
