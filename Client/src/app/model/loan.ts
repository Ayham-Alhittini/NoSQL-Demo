
export interface Loan {
    loanId: string;
    customerId: string;
    loanAmount: number;
    loanType: string;
    interestRate: number;
    startDate: Date;
    loanDuration: number; // In year
    totalLoanAmount: number;
    yearlyPayment: number;
    monthlyPayment: number;
}

