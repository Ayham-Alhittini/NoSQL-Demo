package org.banksystem.banksystemdemo.entities;

import com.decentraldbcluster.dbclient.odm.annotations.Id;

import java.util.UUID;


public class AppUser {

    @Id
    private final String userId = UUID.randomUUID().toString();
    private String username;
    private String password;


    public String getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "AppUser{" +
                "userId='" + userId + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
