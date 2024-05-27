package org.banksystem.banksystemdemo.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.banksystem.banksystemdemo.data.BankSystemDatabase;
import org.banksystem.banksystemdemo.dto.LoginDto;
import org.banksystem.banksystemdemo.dto.RegisterDto;
import org.banksystem.banksystemdemo.dto.UserDto;
import org.banksystem.banksystemdemo.entities.AppUser;
import org.banksystem.banksystemdemo.entities.BankAccount;
import org.banksystem.banksystemdemo.entities.Customer;
import org.banksystem.banksystemdemo.services.TokenService;
import org.banksystem.banksystemdemo.util.Mapper;

@RestController
@RequestMapping("api/auth")
@CrossOrigin("*")
public class AuthenticationController {

    private final BankSystemDatabase db;
    private final TokenService tokenService;

    @Autowired
    public AuthenticationController(BankSystemDatabase db, TokenService tokenService) {
        this.db = db;
        this.tokenService = tokenService;
    }

    @PostMapping("/register")
    public UserDto register(@RequestBody RegisterDto registerDto) {
        // Mapping DTO to entity
        AppUser appUser = Mapper.mapRegisterDtoToAppUser(registerDto);
        Customer customer = Mapper.mapRegisterDtoToCustomer(registerDto);
        customer.setUserId(appUser.getUserId());

        BankAccount bankAccount = new BankAccount();
        bankAccount.setUserId(appUser.getUserId());
        bankAccount.setCustomerId(customer.getId());
        bankAccount.setAccountType(registerDto.accountType);

        // Saving entities to the database
        db.users.save(appUser);
        db.customers.save(customer);
        bankAccount = db.accounts.save(bankAccount); // Retrieve the optional not sent fields

        // Generating token
        String token = tokenService.createToken(appUser.getUserId());
        return new UserDto(appUser.getUserId(), appUser.getUsername(), customer.getId(), bankAccount.getAccountId(), token);
    }

    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody LoginDto loginDto) {
        // Fetching user from the database
        AppUser appUser = db.users.findFirstBy("username", loginDto.username);

        if (appUser == null || !appUser.getPassword().equals(loginDto.password)) {
            return ResponseEntity.status(401).body(null);
        }

        // Retrieving related customer and bank account information
        Customer customer = db.customers.findFirstBy("userId", appUser.getUserId());
        BankAccount bankAccount = db.accounts.findFirstBy("userId", appUser.getUserId());

        // Generating token
        String token = tokenService.createToken(appUser.getUserId());
        UserDto userDto = new UserDto(appUser.getUserId(), appUser.getUsername(), customer.getId(), bankAccount.getAccountId(), token);
        return ResponseEntity.ok(userDto);
    }
}
