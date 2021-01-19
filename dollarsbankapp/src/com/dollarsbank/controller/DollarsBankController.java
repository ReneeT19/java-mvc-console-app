package com.dollarsbank.controller;

import com.dollarsbank.model.Customer;
import com.dollarsbank.model.SavingsAccount;

import java.util.InputMismatchException;
import java.util.Scanner;

import static com.dollarsbank.utility.ConsolePrinterUtility.printBox;
import static com.dollarsbank.utility.ConsolePrinterUtility.printChoice;

public class DollarsBankController {
    private static String customerName = "";
    private static String customerAddress = "";
    private static int customerNumber = 0;
    private static String userId = "";
    private static String password = "";
    private static double initialDeposit=0.0;


    public static Customer customer = new Customer();
    public static Account account = new Account();
    public static Customer payer;
    public static Account payerAccount;
    public static Account payeeAccount;
//    public static Account newAccount = new Account(userId);
    public static Customer payee;

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
        System.out.println("Creating new account...");

        // Header
        printBox("Enter Details for New Account");

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

        payer = new Customer(userId, customerName, customerAddress, customerNumber, password, initialDeposit);
        customer.customerMap.put(userId, payer);
        payee = new Customer(userId, customerName, customerAddress, customerNumber, password, initialDeposit);
        customer.customerMap.put(userId, payee);
        System.out.print("Customer account successfully created.");

        payerAccount = new Account(payer);
        account.accountMap.put(userId, payer);
        payeeAccount = new Account(payee);
        account.accountMap.put(userId, payee);
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
                Customer payer = customer.customerMap.get(userId);
                System.out.println(payer);

                if (payer.password.equals(password)) {
                    printBox("WELCOME Customer");
                    System.out.println("1.Deposit Amount\n2.Withdraw Amount\n3.Funds Transfer\n4.View 5 Recent Transactions\n5.Display Customer Information\n6.Sign Out");
                    printChoice(6);
                    choice = scan.nextInt();
                    switch (choice) {
                        case 1:
                            depositAmount(payer);
                            break;
                        case 2:
                            withdrawAmount(payer);
                            break;
                        case 3: fundsTransfer(payer,payee);
                            break;
                        case 4:
                            viewRecentTransactions(payerAccount);
                            break;
                        case 5:
                            displayCustomerInfo(payer);
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
    public static void depositAmount(Customer payer) throws InterruptedException {
        Scanner scan = new Scanner(System.in);
        printBox("Welcome to the Deposit Portal");
        System.out.println("\nEnter the Amount to Deposit : ");
        String option;
        option = scan.nextLine();
        payerAccount.depositFunds(option);
        System.out.print("\nAmount Deposited Successfully.. \nUpdated Balance: " + Double.sum(payer.initialDeposit, Double.parseDouble(payerAccount.getBalance())) + "\n");
        payerAccount.addTransaction(payerAccount.getBalance() + " deposited to your account.");
        System.out.println("------------------------------------------------------------");
        Thread.sleep(3000);
        System.out.flush();
    }

    //withdrawal method
    public static void withdrawAmount(Customer payer) throws InterruptedException {
        Scanner scan = new Scanner(System.in);
        printBox("Welcome to the Withdraw Portal");
        System.out.println("\nEnter the Amount to Withdraw : ");
        String option;
        option = scan.nextLine();
        payerAccount.withdrawFunds(option);
        System.out.print("\nAmount Withdrawn Successfully.. \nUpdated Balance: " + Double.sum(payer.initialDeposit, Double.parseDouble(payerAccount.getBalance())) + "\n");
        payerAccount.addTransaction(payerAccount.getBalance() + " withdrawn from your account.");
        System.out.println("------------------------------------------------------------");
        Thread.sleep(3000);
        System.out.flush();
    }

    //Funds Transfer Method
    public static void fundsTransfer(Customer payer, Customer payee) throws InterruptedException {
        printBox("Welcome to the Funds Transfer Portal");
        Scanner scan = new Scanner(System.in);
        String userId = "";

        System.out.print("Enter payee user Id : ");
        userId = scan.next();
        scan.nextLine();
        System.out.println("Enter amount : ");
        String option;
        option = scan.nextLine();

        fundsTransfer = scan.nextLine();
        if((Double.parseDouble(fundsTransfer)) > 300000)
        {
            System.out.println("Transfer limit exceeded. Contact bank manager.");
            return;
        }
        if (customer.customerMap.containsKey(userId)) {
            payee = customer.customerMap.get(userId);
            System.out.println(payee);
            payerAccount.withdrawFunds(option);
            payeeAccount.depositFunds(option);
        }
            System.out.print("\nFunds transferred successfully \nUpdated Balance for the payee: " + Double.sum(payee.initialDeposit, Double.parseDouble(payeeAccount.getBalance())) +
                    "\nUpdated Balance for the payer: " + Double.sum(payer.initialDeposit, Double.parseDouble(payerAccount.getBalance())) + "\n");
            payerAccount.addTransaction(payerAccount.getBalance() + " transferred from your account.");
//            else {
//            System.out.println("User Id doesn't exist.");
            Thread.sleep(3000);
            System.out.flush();

    }

    //View 5 Recent Transactions Method
    public static void viewRecentTransactions(Account payerAccount) throws InterruptedException {
        printBox("Welcome to the 5 Recent Transactions Portal");
        for (String transactions : payerAccount.transactions) {
            System.out.println(transactions);
        }
        Thread.sleep(3000);
        System.out.flush();
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
