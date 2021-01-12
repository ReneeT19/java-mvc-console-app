package com.dollarsbank.application;

//import com.dollarsbank.dao.AccountDAOClass;
//import com.dollarsbank.dao.CustomerDAOClass;
import com.dollarsbank.model.Account;
import com.dollarsbank.model.Customer;

import java.util.InputMismatchException;
import java.util.Scanner;

public class DollarsBankApplication {
//    public static CustomerDAOClass customerDB = new CustomerDAOClass();
//    public static AccountDAOClass accountDB = new AccountDAOClass();
    public static Customer customer = new Customer();
    public static Account account = new Account();

    public static void main(String[] args) {
        promptUser();
    }

    public static void promptUser() {
        Scanner scan = new Scanner(System.in);

        boolean running = true;
        while (running) {
            System.out.println("\n[Dollars Bank]");
            System.out.println("Available commands are: \n(1) Create New Account \n(2) Login \n(3) exit");
            System.out.print("Enter Choice (1, 2, or 3): ");
            // Read Command, run through exception
            String command = scan.nextLine();
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
            } catch (InputMismatchException | InterruptedException e) {
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
        double initialDeposit = 0.0;

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
        userId = scan.nextLong();

        // Password
        System.out.print("Password: ");
        password = scan.next();
        scan.nextLine();
        while (!password.matches((("(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*_]).{8,}")))) {
            System.out.println("Invalid Credentials. Try Again!");
            password = scan.next();
        }
        // Initial Deposit Amount
        System.out.print("Initial Deposit Amount: ");
        initialDeposit = scan.nextDouble();
        scan.nextLine();

        Customer newCustomer = new Customer(userId, customerName, customerAddress, customerNumber, password, initialDeposit);
        customer.customerMap.put(userId, newCustomer);
        System.out.print("Customer acocunt successfully created.");
        System.out.print(initialDeposit);
    }

//        if (customerDB.createAccount(newCustomer)) {
//            System.out.println("Customer Account Successfully Created.");
//        } else {
//            System.out.println("Customer Account Could Not Be Created.");
//        }
//    }


    public static void runLogInCommand(Scanner scan) throws InterruptedException {
        long userId = 0;
        String password = "";
        int choice = 0;

        System.out.println("\n[Dollars Bank]\n");
        System.out.println("Enter your log in information...");
        boolean checking = true;
        while (checking) {
            System.out.println("Enter your user ID : ");
            userId = scan.nextLong();
            scan.nextLine();
            System.out.println("Enter password : ");
            password = scan.next();
            scan.nextLine();

            if (customer.customerMap.containsKey(userId)) {
                Customer newCustomer = customer.customerMap.get(userId);
                if (newCustomer.password.equals(password)) {

                    System.out.println("Logged In Successfully. WELCOME customer!!!");
                    System.out.println("choice from below menu: ");
                    System.out.println("1.Deposit Amount\n2.Withdraw Amount\n3.Funds Transfer\n4.View 5 Recent Transactions\n5.Display Customer Information\n6.Sign Out");
                    choice = scan.nextInt();
                    switch (choice) {
                        case 1:
                            depositAmount(newCustomer);
                            break;
                        case 2:
                            withdrawAmount(newCustomer);
                            break;
//                    case 3: fundsTransfer();
//                        break;
                        case 4:
                            viewRecentTransactions();
                            break;
                        case 5:
                            displayCustomerInfo();
                            break;
                        case 6:
                            signOut();
                            break;
                    }

                } else {
                    System.out.println("You have Entered Incorrect User ID or Password. Please Check Again.");
                }
            }
        }
                scan.close();
            }


    //deposit method
    public static void depositAmount(Customer newCustomer) throws InterruptedException {

        Scanner scan = new Scanner(System.in);
        System.out.println("---------------Welcome to Deposit Portal----------------");
//        System.out.println("You are Depositing amount for Account Number: ");
        System.out.println("\nEnter the Amount to Deposit : ");
        String option;
        option = scan.nextLine();
        account.depositFunds(option);
        System.out.print("\nAmount Deposited Successfully.. \nUpdated Balance: " + Double.sum(newCustomer.initialDeposit, Double.parseDouble(account.getBalance())) + "\n");
        System.out.println("------------------------------------------------------------");
        Thread.sleep(5000);
        System.out.flush();
    }

    //withdrawal method
    public static void withdrawAmount(Customer newCustomer) throws InterruptedException {
        Scanner scan = new Scanner(System.in);

        System.out.println("---------------Welcome to Withdrawal Portal----------------");
//        System.out.println("You are Withdrawing amount for Account Number: ");
        System.out.println("\nEnter the Amount to Withdraw : ");
        String option;
        option = scan.nextLine();
        account.withdrawFunds(option);
        System.out.print("\nAmount Withdrawn Successfully.. \nUpdated Balance: " + Double.sum(newCustomer.initialDeposit, Double.parseDouble(account.getBalance())) + "\n");
        System.out.println("------------------------------------------------------------");
        Thread.sleep(5000);
        System.out.flush();
    }

    //Funds Transfer Method
//    public static void fundsTransfer() throws InterruptedException {
//        Scanner scan = new Scanner(System.in);
//        long withdrawAmt;
//        long accountBalance = 500;
//
//        System.out.println("---------------Welcome to Funds Transfer Portal----------------");
//        System.out.println("You are Transferring amount for Account Number: ");
//        System.out.println("\nEnter the Amount to Transfer : ");
//        withdrawAmt = scan.nextLong();
//        accountBalance = -withdrawAmt;
//        System.out.println("Amount Transferred Successfully.. \nUpdated Balance: " + accountBalance);
//        System.out.println("------------------------------------------------------------");
//        Thread.sleep(5000);
//        System.out.flush();
//    }

    //View 5 Recent Transactions Method
    public static void viewRecentTransactions() throws InterruptedException {
        for (String transactions : account.transactions) {
            System.out.println(transactions);
        }
    }

    //Display Customer Information Method
    public static void displayCustomerInfo() throws InterruptedException
    {
        System.out.println("Customer name : "+ customer.getCustomerName());
        System.out.println("Customer address : "+customer.getCustomerAddress());
        System.out.println("Customer contact number : "+customer.getCustomerNumber());
        System.out.println("Customer User Id : "+customer.getUserId());
        System.out.println("Customer Password : "+customer.getPassword());
        System.out.println("Customer Initial Deposit Amount: "+customer.getInitialDeposit());
        System.out.println("------------------------------------------------------------");
        Thread.sleep(5000);
        System.out.flush();
    }

    public static void signOut() throws InterruptedException {
        System.exit(0);
    }

}

