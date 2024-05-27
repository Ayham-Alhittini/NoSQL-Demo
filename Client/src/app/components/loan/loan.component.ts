import { Component, OnInit } from '@angular/core';
import { Loan } from 'src/app/model/loan';
import { LoanService } from 'src/app/services/loan.service';

@Component({
  selector: 'app-loan',
  templateUrl: './loan.component.html',
  styleUrls: ['./loan.component.css']
})
export class LoanComponent implements OnInit {

  loans: Loan[] = [];
  totalDebit = 0;

  constructor(private loanService: LoanService) {}

  ngOnInit(): void {
    this.loanService.getLoans().subscribe(loans => {
      this.loans = loans
      this.totalDebit = 0;
      loans.forEach(loan => this.totalDebit += loan.totalLoanAmount)
    });
    
  }

}
