package org.banksystem.banksystemdemo.api;

import org.banksystem.banksystemdemo.data.BankSystemDatabase;
import org.banksystem.banksystemdemo.entities.AppNotification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/notification")
@CrossOrigin("*")
public class NotificationController {

    private final BankSystemDatabase db;

    @Autowired
    public NotificationController(BankSystemDatabase db) {
        this.db = db;
    }

    @GetMapping("getNotifications/{accountId}")
    public List<AppNotification> getAllNotifications(@PathVariable String accountId) {
        return getNotifications(accountId);
    }

    @PostMapping("markNotificationsAsRead/{accountId}")
    public void markNotificationsAsRead(@PathVariable String accountId) {
        List<AppNotification> unreadNotifications = getUnreadNotifications(accountId);
        for (AppNotification notification: unreadNotifications) notification.setReadDate(LocalDateTime.now());
        db.notifications.saveAll(unreadNotifications);
    }

    private List<AppNotification> getNotifications(String accountId) {
        return db.notifications.findBy("accountId", accountId);
    }

    private List<AppNotification> getUnreadNotifications(String accountId) {
        Map<String, Object> filter = new HashMap<>();
        filter.put("accountId", accountId);
        filter.put("readDate", null);
        return db.notifications.findAll(filter);
    }
}
