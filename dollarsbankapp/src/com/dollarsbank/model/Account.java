package com.dollarsbank.model;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Account {
    private double fundsTransfer;
    private double balance;
    public ArrayList<String> transactions;
    private BigDecimal bigDecimalAccountBalance = BigDecimal.ZERO;

    public Account(Customer newCustomer) {
        transactions  =  new ArrayList<String>(5);
        addTransaction("Initial Deposit: " + newCustomer.initialDeposit);
    }

    public Account(double fundsTransfer, double balance) {
        this.fundsTransfer = fundsTransfer;
        this.balance = balance;
    }


    public double getFundsTransfer() {
        return fundsTransfer;
    }

    public void setFundsTransfer(double fundsTransfer) {
        this.fundsTransfer = fundsTransfer;
    }

    public String getBalance() {
        return bigDecimalAccountBalance.toString();
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void depositFunds(String stringDepositAmount)
    {
        BigDecimal bigDecimalDepositAmount = new BigDecimal(stringDepositAmount);
        bigDecimalAccountBalance = bigDecimalAccountBalance.add(bigDecimalDepositAmount);
    }

    public void withdrawFunds(String stringWithdrawAmount)
    {
        BigDecimal bigDecimalWithdrawAmount = new BigDecimal(stringWithdrawAmount);
        bigDecimalAccountBalance = bigDecimalAccountBalance.subtract(bigDecimalWithdrawAmount);
    }

    public void addTransaction(String message) {
        transactions.add(0,message);
        if(transactions.size()>5) {
            transactions.remove(5);
            transactions.trimToSize();
        }
    }
    @Override
    public String toString() {
        return "Account{" +
                ", fundsTransfer=" + fundsTransfer +
                ", balance=" + balance +
                '}';
    }

}
