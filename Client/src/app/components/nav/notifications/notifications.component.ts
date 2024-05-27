import { Component, OnInit } from '@angular/core';
import { Notification } from 'src/app/model/notification';
import { NotificationService } from 'src/app/services/notification.service';

@Component({
  selector: 'app-notifications',
  templateUrl: './notifications.component.html',
  styleUrls: ['./notifications.component.css']
})
export class NotificationsComponent implements OnInit{
  unreadCount = 0;
  selectedNotification: Notification | null = null;
  constructor(private notificationService: NotificationService) {}

  notifications: Notification[] = [];
  unreadNotifications: Notification[] = [];
  readNotifications: Notification[] = [];

  ngOnInit(): void {
    this.notificationService.getNotifications().subscribe(notifications => {
      this.notifications = notifications;
      this.unreadNotifications = notifications.filter(n => n.readDate === null);
      this.readNotifications = notifications.filter(n => n.readDate != null);
      this.unreadCount = this.unreadNotifications.length;
    });
  }

  notificationShown() {
    this.unreadCount = 0;
    this.notificationService.markNotificationsAsRead().subscribe();
  }
}
