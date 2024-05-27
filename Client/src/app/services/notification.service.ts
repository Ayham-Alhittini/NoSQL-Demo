import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AuthService } from './auth.service';
import { take } from 'rxjs';
import { Notification } from '../model/notification';

@Injectable({
  providedIn: 'root'
})
export class NotificationService {

  accountId: string;
  constructor(private http : HttpClient, private authService: AuthService) {
    this.authService.loadedUser.pipe(take(1)).subscribe(user => this.accountId = user.accountId);
  }

  private baseUrl = "http://localhost:4000/api/notification";

  getNotifications() {
    return this.http.get<Notification[]>(this.baseUrl + '/getNotifications/' + this.accountId);
  }

  markNotificationsAsRead() {
    return this.http.post(this.baseUrl + '/markNotificationsAsRead/' + this.accountId, null);
  }

}
