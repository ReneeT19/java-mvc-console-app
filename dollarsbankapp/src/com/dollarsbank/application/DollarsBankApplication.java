package com.dollarsbank.application;

import com.dollarsbank.dao.AccountDAOClass;
import com.dollarsbank.dao.CustomerDAOClass;
import com.dollarsbank.model.Customer;

import java.util.InputMismatchException;
import java.util.Scanner;

public class DollarsBankApplication {
    public static CustomerDAOClass customerDB = new CustomerDAOClass();
    public static AccountDAOClass accountDB = new AccountDAOClass();

    public static void main(String[] args) {promptUser();}

        public static void promptUser() {
            Scanner scan = new Scanner(System.in);
            boolean running = true;
            while (running) {
                System.out.println("\n[Dollars Bank]");
                System.out.println("Available commands are: \n(1) Create New Account \n(2) Login \n(3) exit");
                System.out.print("Enter Choice (1, 2, or 3): ");
                // Read Command, run through exception
                String command = scan.nextLine();
                int id = 0;
                String lastName = "";
                try {
                    switch (command) {
                        case "Create New Account":
                        case "1":
                            runCreateAccountCommand(scan);
                            break;
                        case "Login":
                        case "2":
                            runLogInCommand(scan);
                            break;
                        case "exit":
                        case "3":
                            running = false;
                            break;
                        default:
                            System.out.println("Incorrect command. Please enter the numeric value or string corresponding with the available commands.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Incorrect input");
                }
            }
            scan.close();
        }

        public static void runCreateAccountCommand(Scanner scan) {
            System.out.println("\n[Dollars Bank]\n");
            System.out.println("Creating new account...");
            String customerName = "";
            String customerAddress = "";
            int customerNumber = 0;
            long userId = 0;
            String password = "";
            double initialDeposit = 0;

            // Name
            System.out.print("Customer Name: ");
            customerName = scan.nextLine();

            // Address
            System.out.print("Customer Address: ");
            customerAddress = scan.nextLine();

            // Contact Number
            System.out.print("Customer Contact Number: ");
            customerNumber = scan.nextInt();

            // User ID
            System.out.print("User Id: ");
            userId = scan.nextInt();

            // Password
            System.out.print("Password: ");
            password = scan.nextLine();

            // Initial Deposit Amount
            System.out.print("Initial Deposit Amount: ");
            initialDeposit = scan.nextInt();

            int accoId = 1;

            Customer newCustomer = new Customer(-1, customerName, customerAddress, customerNumber, password, initialDeposit, accoId);
            if (customerDB.createAccount(newCustomer)) {
                System.out.println("Customer Account Successfully Created.");
            } else {
                System.out.println("Customer Account Could Not Be Created.");
            }
        }


        public static void runLogInCommand(Scanner scan) {
            System.out.println("\n[Dollars Bank]\n");
            System.out.println("Enter your log in information...");

        }

    }

