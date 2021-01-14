package com.dollarsbank.controller;

import com.dollarsbank.model.Customer;
import com.dollarsbank.model.SavingsAccount;

import java.util.InputMismatchException;
import java.util.Scanner;

import static com.dollarsbank.utility.ConsolePrinterUtility.printBox;
import static com.dollarsbank.utility.ConsolePrinterUtility.printChoice;

public class DollarsBankController {

    public static Customer customer = new Customer();
    public static SavingsAccount savingsAccount = new SavingsAccount();

    public static void promptUser() {
        Scanner scan = new Scanner(System.in);

        boolean running = true;
        while (running) {
            printBox("DOLLARBANKS Welcomes You!");
            System.out.println("\n(1) Create New Account \n(2) Login \n(3) exit");
            printChoice(3);
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
        printBox("Enter Details for New Account");
        String customerName = "";
        String customerAddress = "";
        int customerNumber = 0;
        String userId = "";
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
        userId = scan.next();
        scan.nextLine();

        // Password
        System.out.print("Password (8 characters with lower, upper, numbers, and special): ");
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
        System.out.print("Customer acocunt successfully created." + "\n");
        System.out.println(newCustomer);

        SavingsAccount newAccount = new SavingsAccount(userId);
        savingsAccount.savingsAccountMap.put(userId, newAccount);
        System.out.println(newAccount);
    }

    public static void runLogInCommand(Scanner scan) throws InterruptedException {
        printBox("Enter Login Details");
        String userId = "";
        String password = "";
        int choice = 0;

        boolean checking = true;
        while (checking) {
            System.out.println("Enter your user ID : ");
            userId = scan.next();
            scan.nextLine();
            System.out.println("Enter password : ");
            password = scan.next();
            scan.nextLine();

            if (customer.customerMap.containsKey(userId)) {
                Customer newCustomer = customer.customerMap.get(userId);
                savingsAccount = new SavingsAccount(newCustomer);

                if (newCustomer.password.equals(password)) {
                    printBox("WELCOME Customer");
                    System.out.println("1.Deposit Amount\n2.Withdraw Amount\n3.Funds Transfer\n4.View 5 Recent Transactions\n5.Display Customer Information\n6.Sign Out");
                    printChoice(6);
                    choice = scan.nextInt();
                    switch (choice) {
                        case 1:
                            depositAmount(newCustomer);
                            break;
                        case 2:
                            withdrawAmount(newCustomer);
                            break;
                        case 3: fundsTransfer(newCustomer);
                            break;
                        case 4:
                            viewRecentTransactions(savingsAccount);
                            break;
                        case 5:
                            displayCustomerInfo(newCustomer);
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
        printBox("Welcome to the Deposit Portal");
        System.out.println("\nEnter the Amount to Deposit : ");
        String option;
        option = scan.nextLine();
        savingsAccount.depositFunds(option);
        if(Double.parseDouble(option) > 10000) {
            System.out.println("You can't deposit more than 10000 at a time.");
            return;
        }
            System.out.print("\nAmount Deposited Successfully.. \nUpdated Balance: " + Double.sum(newCustomer.initialDeposit, Double.parseDouble(savingsAccount.getBalance())) + "\n");
        savingsAccount.addTransaction(savingsAccount.getBalance() + " deposited to your account.");
        System.out.println("------------------------------------------------------------");
        Thread.sleep(3000);
        System.out.flush();
    }

    //withdrawal method
    public static void withdrawAmount(Customer newCustomer) throws InterruptedException {
        Scanner scan = new Scanner(System.in);
        printBox("Welcome to the Withdraw Portal");
        System.out.println("\nEnter the Amount to Withdraw : ");
        String option;
        option = scan.nextLine();
        savingsAccount.withdrawFunds(option);
        if(Double.parseDouble(option) > Double.sum(newCustomer.initialDeposit, Double.parseDouble(savingsAccount.getBalance()))) {
            System.out.println("Insufficient balance.");
            return;
        }
        System.out.print("\nAmount Withdrawn Successfully.. \nUpdated Balance: " + Double.sum(newCustomer.initialDeposit, Double.parseDouble(savingsAccount.getBalance())) + "\n");
        savingsAccount.addTransaction(savingsAccount.getBalance() + " withdrawn from your account.");
        System.out.println("------------------------------------------------------------");
        Thread.sleep(3000);
        System.out.flush();
    }

//    Funds Transfer Method
    public static void fundsTransfer(Customer newCustomer) throws InterruptedException {
        printBox("Welcome to the Funds Transfer Portal");
        Scanner scan = new Scanner(System.in);
        String userId = "";
        String fundsTransfer = "";

        System.out.print("Enter payee user Id : ");
        userId = scan.next();
        scan.nextLine();
        System.out.println("Enter amount : ");

        fundsTransfer = scan.nextLine();
        if((Double.parseDouble(fundsTransfer)) > 300000)
        {
            System.out.println("Transfer limit exceeded. Contact bank manager.");
            return;
        }
        if (customer.customerMap.containsKey(userId)) {
            Customer payee = customer.customerMap.get(userId);
            SavingsAccount payeeAccount = new SavingsAccount(payee);
            payeeAccount.depositFunds(fundsTransfer);
            savingsAccount.withdrawFunds(fundsTransfer);
            System.out.print("\nFunds transferred successfully \nUpdated Balance for the payee: " + Double.sum(payee.initialDeposit, Double.parseDouble(payeeAccount.getBalance())) +
                    "\nUpdated Balance for the payer: " + Double.sum(newCustomer.initialDeposit, Double.parseDouble(savingsAccount.getBalance())) + "\n");
            savingsAccount.addTransaction(savingsAccount.getBalance() + " transferred from your account.");
        } else {
            System.out.println("User Id doesn't exist.");
        }
    }

    //View 5 Recent Transactions Method
    public static void viewRecentTransactions(SavingsAccount savingsAccount) throws InterruptedException {
        printBox("Welcome to the 5 Recent Transactions Portal");
        for (String transactions : savingsAccount.transactions) {
            System.out.println(transactions);
        }
    }

    //Display Customer Information Method
    public static void displayCustomerInfo(Customer newCustomer) throws InterruptedException
    {
        printBox("Welcome to the Customer Information Portal");
        System.out.println("Customer name : "+ newCustomer.getCustomerName());
        System.out.println("Customer address : "+newCustomer.getCustomerAddress());
        System.out.println("Customer contact number : "+newCustomer.getCustomerNumber());
        System.out.println("Customer User Id : "+newCustomer.getUserId());
        System.out.println("Customer Password : "+newCustomer.getPassword());
        System.out.println("Customer Initial Deposit Amount: "+newCustomer.getInitialDeposit());
        System.out.println("------------------------------------------------------------");
        Thread.sleep(3000);
        System.out.flush();
    }

    public static void signOut() throws InterruptedException {
        System.exit(0);
    }
}
