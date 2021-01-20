//package com.dollarsbank.model;
//
//import java.math.BigDecimal;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Map;
//
//public class SavingsAccount implements Account {
//    private String userId;
//    private String stringAccountBalance;
//    public ArrayList<String> transactions;
//    private BigDecimal bigDecimalAccountBalance = BigDecimal.ZERO;
//
//
//    public Map<String,SavingsAccount> savingsAccountMap;
//
//    public SavingsAccount() {
//        savingsAccountMap = new HashMap<String,SavingsAccount>();
//    }
//
//    public SavingsAccount(Customer newCustomer) {
//        transactions  =  new ArrayList<String>(5);
//        addTransaction("Initial Deposit: " + newCustomer.initialDeposit);
//    }
//
//    public SavingsAccount(String userId) {
//        this.userId = userId;
//    }
//
//    public SavingsAccount(String userId, String stringAccountBalance, String stringFundsTransfer) {
//        this.userId = userId;
//        this.stringAccountBalance = stringAccountBalance;
//    }
//
//    public String getUserId() {
//        return userId;
//    }
//
//    public void setUserId(String userId) {
//        this.userId = userId;
//    }
//
//    public String getBalance() {
//        return bigDecimalAccountBalance.toString();
//    }
//
//    public void setBalance(String stringAccountBalance)
//    {
//        this.stringAccountBalance = stringAccountBalance;
//        bigDecimalAccountBalance = new BigDecimal(stringAccountBalance);
//    }
//
//    public void depositFunds(String stringDepositAmount)
//    {
//        BigDecimal bigDecimalDepositAmount = new BigDecimal(stringDepositAmount);
//        bigDecimalAccountBalance = bigDecimalAccountBalance.add(bigDecimalDepositAmount);
//    }
//
//    public void withdrawFunds(String stringWithdrawAmount)
//    {
//        BigDecimal bigDecimalWithdrawAmount = new BigDecimal(stringWithdrawAmount);
//        bigDecimalAccountBalance = bigDecimalAccountBalance.subtract(bigDecimalWithdrawAmount);
//    }
//
//    public void addTransaction(String message) {
//        transactions.add(0,message);
//        if(transactions.size()>5) {
//            transactions.remove(5);
//            transactions.trimToSize();
//        }
//    }
//
//    @Override
//    public String toString() {
//        return "SavingsAccount{" +
//                "userId='" + userId + '\'' +
//                ", transactions=" + transactions +
//                ", bigDecimalAccountBalance=" + bigDecimalAccountBalance +
//                ", stringAccountBalance='" + stringAccountBalance + '\'' +
//                ", savingsAccountMap=" + savingsAccountMap +
//                '}';
//    }
//}
