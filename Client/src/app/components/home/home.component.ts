import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/model/user';
import { AccountService } from 'src/app/services/account.service';
import { AuthService } from 'src/app/services/auth.service';
@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  user: User;
  userBalance: number;
  
  constructor(private authService: AuthService, private accountService: AccountService){}

  ngOnInit(): void {
    this.authService.loadedUser.subscribe(user => {
      this.user = user;
      this.accountService.getAccountBalance()
        .subscribe(balance => this.userBalance = balance)
    })
  }
}