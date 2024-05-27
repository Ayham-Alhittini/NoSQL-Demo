import { HttpClient } from '@angular/common/http';
import { Injectable, OnInit } from '@angular/core';
import { AuthService } from './auth.service';
import { take } from 'rxjs';
import { Transaction } from '../model/transaction';

@Injectable({
  providedIn: 'root'
})
export class TransactionService {

  private accountId: string;

  constructor(private http : HttpClient, private authServie: AuthService) {
    this.authServie.loadedUser.pipe(take(1)).subscribe(user => this.accountId = user.accountId);
   }
  
  private baseUrl = "http://localhost:4000/api/transaction";

  deposit(amount: number) {
    return this.http.post(this.baseUrl + '/deposit/' + this.accountId + '?amount=' + amount, null);
  }

  withdraw(amount: number) {
    return this.http.post(this.baseUrl + '/withdraw/' + this.accountId + '?amount=' + amount, null);
  }

  transfer(model: any) {
    return this.http.post(this.baseUrl + '/transfer/' + this.accountId, model);
  }

  getTransactions() {
    return this.http.get<Transaction[]>(this.baseUrl + '/getTransactions/' + this.accountId);
  }

}
