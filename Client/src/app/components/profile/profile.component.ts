import { Component, OnInit } from '@angular/core';
import { take } from 'rxjs';
import { Customer } from 'src/app/model/Customer';
import { AccountService } from 'src/app/services/account.service';
import { AuthService } from 'src/app/services/auth.service';
import { CustomerService } from 'src/app/services/customer.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  
  customer: Customer;
  account: any;

  constructor(public authService: AuthService, 
    private customerService: CustomerService,
    private accountService: AccountService){}

  ngOnInit(): void {
    this.authService.loadedUser.pipe(take(1)).subscribe(user => {
      this.customerService.getCustomer(user.customerId).subscribe(customer => this.customer = customer);
      this.accountService.getAccountDetails().subscribe(account => this.account = account);
    });
  }


}
