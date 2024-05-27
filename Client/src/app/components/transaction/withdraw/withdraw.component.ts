import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { TransactionService } from 'src/app/services/transaction.service';

@Component({
  selector: 'app-withdraw',
  templateUrl: './withdraw.component.html',
  styleUrls: ['./withdraw.component.css']
})
export class WithdrawComponent {

  amount = 0;

  constructor(private router: Router, private toaster: ToastrService, 
    private transcationService: TransactionService){}
    
  closeModel() {
    this.router.navigateByUrl("/transactions");
  }

  onSubmit() {
    this.transcationService.withdraw(this.amount).subscribe({
      next: () => {
        this.toaster.success(this.amount + " is withdrawed from your account");
        this.closeModel();
      }, error: err => {
        this.toaster.error(err.error.error);
        
      }
    })
  }
}
