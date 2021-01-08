package com.dollarsbank.dao;

import com.dollarsbank.ConnectionManager;
import com.dollarsbank.model.Customer;
import com.dollarsbank.model.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CustomerDAOClass implements CustomerDAO {
    private Connection connection = ConnectionManager.getConnection();
    private static final String TABLE_NAME = "customer";
    private Customer customer;

    @Override
    public boolean createAccount(Customer customer) {
        this.customer = customer;
        // TODO Auto-generated method stub

        String query = "INSERT INTO " + TABLE_NAME +
                " (user_id, customer_name, customer_address, customer_number, account_password, initial_deposit, acco_id) " +
                " values (null, ?, ?, ?, ?, ?, ?)";

        boolean isSuccessful = false;

        try (PreparedStatement stmt = connection.prepareStatement(query);){

            stmt.setString(1, customer.getCustomerName());
            stmt.setString(2, customer.getCustomerAddress());
            stmt.setInt(3, customer.getCustomerNumber());
            stmt.setString(4, customer.getPassword());
            stmt.setDouble(5, customer.getInitialDeposit());
            stmt.setLong(6, customer.getAccountId());

            int rowCount = stmt.executeUpdate();

            if (rowCount > 0 ) {
                isSuccessful = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return isSuccessful;
    }
}