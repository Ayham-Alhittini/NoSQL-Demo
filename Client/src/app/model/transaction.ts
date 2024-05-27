export interface Transaction {
    transactionId: string;
    accountId: string;
    destinationAccountId: string;
    transactionType: string;
    amount: number;
    transactionDate: Date;
}

