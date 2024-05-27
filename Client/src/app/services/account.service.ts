import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AuthService } from './auth.service';
import { take } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AccountService {

  accountId: string;
  constructor(private http : HttpClient, private authService: AuthService) {
    this.authService.loadedUser.pipe(take(1)).subscribe(user => this.accountId = user.accountId);
  }


  private baseUrl = "http://localhost:4000/api/account";

  getAccountBalance() {
    return this.http.get<number>(this.baseUrl + "/getBalance/" + this.accountId);
  }

  getAccountDetails() {
    return this.http.get(this.baseUrl + "/getDetails/" + this.accountId);
  }
}
