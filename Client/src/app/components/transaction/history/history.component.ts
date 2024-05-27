import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { take } from 'rxjs';
import { Transaction } from 'src/app/model/transaction';
import { User } from 'src/app/model/user';
import { AuthService } from 'src/app/services/auth.service';
import { TransactionService } from 'src/app/services/transaction.service';

@Component({
  selector: 'app-history',
  templateUrl: './history.component.html',
  styleUrls: ['./history.component.css']
})
export class HistoryComponent implements OnInit{
  

  transactions: Transaction[];
  user: User;

  constructor(private transactionService: TransactionService, private authService: AuthService, private router: Router){}

  ngOnInit(): void {
    this.transactionService.getTransactions().subscribe(transactions => this.transactions = transactions);
    this.authService.loadedUser.pipe(take(1)).subscribe(user => this.user = user);
  }

  isUserSender(transaction: Transaction): boolean {
    return transaction.accountId === this.user.accountId;
  }

  isUserReceiver(transaction: Transaction): boolean {
    return transaction.destinationAccountId === this.user.accountId;
  }

  getTransactionClass(transaction: Transaction): string {
    if (transaction.transactionType === 'TRANSFER') {
      if (this.isUserReceiver(transaction)) {
        return 'received'; // Class for received transfer
      }
      return this.isUserSender(transaction) ? 'transfer' : '';
    }
    return transaction.transactionType.toLowerCase(); // 'deposit' or 'withdraw'
  }

  formatAmount(transaction: Transaction): string {
    let sign = '';
    if (transaction.transactionType === 'WITHDRAW' || this.isUserSender(transaction)) {
      sign = '-'; // Prefix with minus for withdrawals and sent transfers
    } else if (transaction.transactionType === 'DEPOSIT' || this.isUserReceiver(transaction)) {
      sign = '+'; // Prefix with plus for deposits and received transfers
    }
    return `${sign}${transaction.amount.toFixed(2)}`;
  }

  closeHistory() {
    this.router.navigate(['/transactions']); // Adjust this as needed to match your routing
  }

  capitalizeWords(str: string): string {
    if (!str) return str;
    return str.toLowerCase().split(' ').map(word => word[0].toUpperCase() + word.substr(1)).join(' ');
  }

}
