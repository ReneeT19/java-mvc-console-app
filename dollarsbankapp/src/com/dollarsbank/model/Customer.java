package com.dollarsbank.model;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Customer extends SavingsAccount {

    private String customerName;
    private String customerAddress;
    private int customerNumber;
    private String userId;
    public String password;
    public double initialDeposit;
    private BigDecimal bigDecimalInitialDeposit = BigDecimal.ZERO;


    public Map<String,Customer> customerMap;
    public Customer() {
        customerMap = new HashMap<String,Customer>();
    }

    public Customer(String userId, String customerName, String customerAddress, int customerNumber, String password, double initialDeposit) {
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.userId = userId;
        this.customerNumber = customerNumber;
        this.password = password;
        this.initialDeposit = initialDeposit;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getInitialDeposit() {
        return initialDeposit;    }

    public void setInitialDeposit(double initialDeposit) {
        this.initialDeposit = initialDeposit;
    }

    public int getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(int customerNumber) {
        this.customerNumber = customerNumber;
    }


    @java.lang.Override
    public java.lang.String toString() {
        return "Customer{" +
                "customerName='" + customerName + '\'' +
                ", customerAddress='" + customerAddress + '\'' +
                ", customerNumber=" + customerNumber +
                ", userId=" + userId +
                ", password='" + password + '\'' +
                ", initialDeposit=" + initialDeposit +
                '}';
    }
}