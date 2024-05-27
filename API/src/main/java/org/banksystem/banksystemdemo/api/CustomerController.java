package org.banksystem.banksystemdemo.api;

import org.banksystem.banksystemdemo.data.BankSystemDatabase;
import org.banksystem.banksystemdemo.dto.CustomerDto;
import org.banksystem.banksystemdemo.entities.Customer;
import org.banksystem.banksystemdemo.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customer")
@CrossOrigin("*")
public class CustomerController {
    private final BankSystemDatabase db;

    @Autowired
    public CustomerController(BankSystemDatabase db) {
        this.db = db;
    }

    @GetMapping("{customerId}")
    public CustomerDto getCustomerById(@PathVariable String customerId) {
        Customer customer = db.customers.findById(customerId);
        return Mapper.mapCustomerToCustomerDto(customer);
    }

}
