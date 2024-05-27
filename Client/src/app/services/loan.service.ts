import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from '../model/user';
import { AuthService } from './auth.service';
import { take } from 'rxjs';
import { Loan } from '../model/loan';

@Injectable({
  providedIn: 'root'
})
export class LoanService {

  user:User;

  constructor(private http : HttpClient, private authService: AuthService) {
    this.authService.loadedUser.pipe(take(1)).subscribe(user => this.user = user);
  }

  private baseUrl = "http://localhost:4000/api/loan";

  apply(model: any) {
    return this.http.post(this.baseUrl + '/apply/' + this.user.customerId, model);
  }

  getLoans() {
    return this.http.get<Loan[]>(this.baseUrl + '/getLoans/' + this.user.customerId);
  }


}
