package org.banksystem.banksystemdemo.entities;

import com.decentraldbcluster.dbclient.odm.annotations.ForeignKey;
import com.decentraldbcluster.dbclient.odm.annotations.Id;
import org.banksystem.banksystemdemo.enums.LoanType;

import java.time.LocalDateTime;

public class Loan {
    @Id
    private String loanId;
    @ForeignKey
    private String customerId;
    private Long loanAmount;
    private LoanType loanType;
    private int interestRate;
    private final LocalDateTime startDate = LocalDateTime.now();
    private int loanDuration;// In year
    private Long totalLoanAmount;
    private Double yearlyPayment;
    private Double monthlyPayment;


    public String getLoanId() {
        return loanId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Long getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(Long loanAmount) {
        this.loanAmount = loanAmount;
    }

    public LoanType getLoanType() {
        return loanType;
    }

    public void setLoanType(LoanType loanType) {
        this.loanType = loanType;
    }

    public int getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(int interestRate) {
        this.interestRate = interestRate;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public int getLoanDuration() {
        return loanDuration;
    }

    public void setLoanDuration(int loanDuration) {
        this.loanDuration = loanDuration;
    }


    @Override
    public String toString() {
        return "Loan{" +
                "loanId='" + loanId + '\'' +
                ", customerId='" + customerId + '\'' +
                ", loanAmount=" + loanAmount +
                ", loanType=" + loanType +
                ", interestRate=" + interestRate +
                ", startDate=" + startDate +
                ", loanYearsDuration=" + loanDuration +
                '}';
    }

    public Double getMonthlyPayment() {
        return monthlyPayment;
    }

    public void setMonthlyPayment(Double monthlyPayment) {
        this.monthlyPayment = monthlyPayment;
    }

    public Long getTotalLoanAmount() {
        return totalLoanAmount;
    }

    public void setTotalLoanAmount(Long totalLoanAmount) {
        this.totalLoanAmount = totalLoanAmount;
    }

    public Double getYearlyPayment() {
        return yearlyPayment;
    }

    public void setYearlyPayment(Double yearlyPayment) {
        this.yearlyPayment = yearlyPayment;
    }

    public void setLoanId(String loanId) {
        this.loanId = loanId;
    }
}
