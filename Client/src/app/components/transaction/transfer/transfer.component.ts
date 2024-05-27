import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { TransactionService } from 'src/app/services/transaction.service';

@Component({
  selector: 'app-transfer',
  templateUrl: './transfer.component.html',
  styleUrls: ['./transfer.component.css']
})
export class TransferComponent {
  
  transfer = {
    transferAmount:0,
    destinationAccountId: ''
  }

  constructor(private router: Router, private toaster: ToastrService, 
    private transactionService: TransactionService){}

  closeModel() {
    this.router.navigateByUrl("/transactions");
  }

  onSubmit() {
    this.transactionService.transfer(this.transfer).subscribe({
      next: () => {
        this.toaster.success(this.transfer.transferAmount + " is tranfored to " + this.transfer.destinationAccountId);
        this.closeModel();
      }, error : err => {
        this.toaster.error(err.error.error);
      }
    })
  }

}
