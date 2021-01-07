package com.dollarsbank.model;

public class Customer {

    private String customerName;
    private String customerAddress;
    private int customerNumber;
    private long userId;
    private String password;
    private double initialDeposit;

    public Customer() {
    }

    public Customer(String customerName, String customerAddress, int customerNumber, long userId, String password, double initialDeposit) {
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.userId = userId;
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

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getInitialDeposit() {
        return initialDeposit;
    }

    public void setInitialDeposit(double initialDeposit) {
        this.initialDeposit = initialDeposit;
    }

    public int getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(int customerNumber) {
        this.customerNumber = customerNumber;
    }

    @Override
    public String toString() {
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