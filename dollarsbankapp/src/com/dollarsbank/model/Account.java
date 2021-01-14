package com.dollarsbank.model;

public interface Account {
    void depositFunds(String stringDepositAmount);
    void withdrawFunds(String stringWithdrawAmount);
    void addTransaction(String message);
}
