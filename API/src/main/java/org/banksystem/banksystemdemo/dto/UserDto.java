package org.banksystem.banksystemdemo.dto;

public class UserDto {
    public String userId;
    public String username;
    public String customerId;
    public String accountId;
    public String token;

    public UserDto(String userId, String username , String customerId, String accountId, String token) {
        this.userId = userId;
        this.username = username;
        this.customerId = customerId;
        this.accountId = accountId;
        this.token = token;
    }
}
