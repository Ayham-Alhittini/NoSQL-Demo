import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { LoanService } from 'src/app/services/loan.service';

@Component({
  selector: 'app-loan-apply',
  templateUrl: './loan-apply.component.html',
  styleUrls: ['./loan-apply.component.css']
})
export class LoanApplyComponent {

  newLoan: any = {
    loanAmount:0,
    loanDuration:0,
    loanType: 'PERSONAL'
  }
  constructor(private router: Router, private loanService: LoanService, private toaster: ToastrService){}
  
  applyForLoan() {
    this.loanService.apply(this.newLoan).subscribe(() => {
      this.closeModel();
      this.toaster.success('Process goes succussfully')
    })
  }

  closeModel() {
    this.router.navigateByUrl("/loans");
  }
}
