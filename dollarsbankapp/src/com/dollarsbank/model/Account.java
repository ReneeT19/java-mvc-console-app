package com.dollarsbank.model;

public class Account {
    private long accountId;
    private double deposit;
    private double withdraw;
    private double fundsTransfer;
    private double balance;

    public Account() {
    }

    public Account(long accountId, double deposit, double withdraw, double fundsTransfer, double balance) {
        this.accountId = accountId;
        this.deposit = deposit;
        this.withdraw = withdraw;
        this.fundsTransfer = fundsTransfer;
        this.balance = balance;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public double getDeposit() {
        return deposit;
    }

    public void setDeposit(double deposit) {
        this.deposit = deposit;
    }

    public double getWithdraw() {
        return withdraw;
    }

    public void setWithdraw(double withdraw) {
        this.withdraw = withdraw;
    }

    public double getFundsTransfer() {
        return fundsTransfer;
    }

    public void setFundsTransfer(double fundsTransfer) {
        this.fundsTransfer = fundsTransfer;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountId=" + accountId +
                ", deposit=" + deposit +
                ", withdraw=" + withdraw +
                ", fundsTransfer=" + fundsTransfer +
                ", balance=" + balance +
                '}';
    }
}
