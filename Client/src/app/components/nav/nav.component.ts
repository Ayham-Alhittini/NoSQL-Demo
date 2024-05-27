import { Component, OnInit } from '@angular/core';
import { take } from 'rxjs';
import { AuthService } from 'src/app/services/auth.service';
import { CustomerService } from 'src/app/services/customer.service';

@Component({
  selector: 'app-nav',
  templateUrl: './nav.component.html',
  styleUrls: ['./nav.component.css']
})
export class NavComponent implements OnInit {

  userFullNameAbbreviation: string;

  constructor(private authService: AuthService, private customerService: CustomerService) { }  

  ngOnInit(): void {
    this.authService.loadedUser.pipe(take(1)).subscribe(user => {
      this.customerService.getCustomer(user.customerId).subscribe(customer => {
        this.userFullNameAbbreviation = customer.firstName.charAt(0) + customer.lastName.charAt(0);
        this.userFullNameAbbreviation = this.userFullNameAbbreviation.toUpperCase();  
      })
    })
  }

}

