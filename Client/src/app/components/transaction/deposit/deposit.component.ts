import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { take } from 'rxjs';
import { AuthService } from 'src/app/services/auth.service';
import { TransactionService } from 'src/app/services/transaction.service';

@Component({
  selector: 'app-deposit',
  templateUrl: './deposit.component.html',
  styleUrls: ['./deposit.component.css']
})
export class DepositComponent{

  amount = 0;

  constructor(private router: Router, private toaster: ToastrService,
    private transactionService: TransactionService){}

  closeModel() {
    this.router.navigateByUrl("/transactions");
  }
  onSubmit() {
    this.transactionService.deposit(this.amount).subscribe(() => {
      this.toaster.success(this.amount + " is deposted to your account");
      this.closeModel();
    });
  }
}
