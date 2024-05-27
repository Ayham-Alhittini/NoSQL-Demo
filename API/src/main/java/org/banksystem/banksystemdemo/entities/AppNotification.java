package org.banksystem.banksystemdemo.entities;

import com.decentraldbcluster.dbclient.odm.annotations.ForeignKey;
import com.decentraldbcluster.dbclient.odm.annotations.Id;

import java.time.LocalDateTime;

public class AppNotification {

    public AppNotification() {}

    public AppNotification(String accountId, String notificationMessage) {
        this.accountId = accountId;
        this.notificationMessage = notificationMessage;
    }

    @Id
    private String notificationId;

    @ForeignKey
    private String accountId;

    private String notificationMessage;

    private LocalDateTime readDate = null;




    public String getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(String notificationId) {
        this.notificationId = notificationId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getNotificationMessage() {
        return notificationMessage;
    }

    public void setNotificationMessage(String notificationMessage) {
        this.notificationMessage = notificationMessage;
    }

    public LocalDateTime getReadDate() {
        return readDate;
    }

    public void setReadDate(LocalDateTime readDate) {
        this.readDate = readDate;
    }
}
