import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static List<Account> accountArrayList = new ArrayList<>(); //Class variable arraylist accountArrayList

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        int choice = 1;

        System.out.println("Welcome to my bank!");

        while( choice != 0) { //Main while loop for program
            choice = menu();

            switch(choice) { //Switch Menu
                case 1:

                    createAccount();
                    break;

                case 2:
                    checkBalance();
                    break;

                case 3:
                    depositMoney();
                    break;

                case 4:
                    withdraw();
                    break;

                case 5:
                    transfer();
                    break;

                case 6:
                    closeAccount();
                    break;

                case 9:
                    System.out.println(accountArrayList);
                    break;

                case 0:
                    System.out.println("Have a Good Day!");
                    break;

                default:
                    System.out.println("Please enter a valid entry");
                    break;
            }
        }
    }
    public static void createAccount() {
        //Gather account information
        Scanner scan = new Scanner(System.in);
        System.out.println("Please Enter a name for the account");
        String name = scan.nextLine();
        System.out.println("Please pick an account number");
        int accountNumber = scan.nextInt();
        System.out.println("Please pick an account PIN");
        int pin = scan.nextInt();
        System.out.println("Please Enter an initial balance");
        double accountBalance = scan.nextDouble();
        scan.nextLine();
        accountArrayList.add(new Account(name, accountNumber, pin, accountBalance)); //Create account using user provided variables
    }


    public static void depositMoney() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Deposit");
        System.out.println("___________________________");
        System.out.println("Enter Account Number");
        int accountNumber = scan.nextInt();
        scan.nextLine();
        System.out.println("Enter Account PIN");
        int pin = scan.nextInt();
        scan.nextLine();
        System.out.println("Enter Deposit Amount");
        double deposit = scan.nextDouble();
        scan.nextLine();

        for (int j = 0; j < accountArrayList.size(); j++) { //Locate correct account
            int acctCheck = accountArrayList.get(j).getAccountNumber();
            if (acctCheck == accountNumber && pin == accountArrayList.get(j).getPin()) {
                accountArrayList.get(j).setAccountBalance(deposit + accountArrayList.get(j).getAccountBalance()); //Add deposit amount to account balance for account
            }
            else { System.out.println("Account Number or PIN incorrect"); }
        }
    }

    public static void checkBalance() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Check Balance");
        System.out.println("___________________________");
        System.out.println("Enter Account Number");
        int accountNumber = scan.nextInt();
        System.out.println("Enter Account PIN");
        int pin = scan.nextInt();
        scan.nextLine();

        for (int j = 0; j < accountArrayList.size(); j++) { //Locate correct account
            int acctCheck = accountArrayList.get(j).getAccountNumber();
            if (acctCheck == accountNumber && pin == accountArrayList.get(j).getPin()) {
                double balance = accountArrayList.get(j).getAccountBalance(); //Return balance
                System.out.println(balance + "\n");
            }
            else { System.out.println("Account Number or PIN incorrect"); }
        }

    }

    public static void withdraw() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Withdraw");
        System.out.println("___________________________");
        System.out.println("Enter Account Number");
        int accountNumber = scan.nextInt();
        scan.nextLine();
        System.out.println("Enter Account PIN");
        int pin = scan.nextInt();
        scan.nextLine();
        System.out.println("Enter Withdraw Amount");
        double withdraw = scan.nextDouble();
        scan.nextLine();

        for (int j = 0; j < accountArrayList.size(); j++) { //Locate correct account
            int acctCheck = accountArrayList.get(j).getAccountNumber();
            if (acctCheck == accountNumber && pin == accountArrayList.get(j).getPin()) {
                accountArrayList.get(j).setAccountBalance(accountArrayList.get(j).getAccountBalance() - withdraw); //Subtract withdraw from account balance
            }
            else { System.out.println("Account Number or PIN incorrect"); }
        }
    }

    public static int menu() { //Menu method
        Scanner scan = new Scanner(System.in);
        System.out.println("___________________________");
        System.out.println("Please make a choice below:");
        System.out.println("___________________________");
        System.out.println("1 : New Account");
        System.out.println("2 : Check Balance");
        System.out.println("3 : Deposit");
        System.out.println("4 : Withdraw");
        System.out.println("5 : Transfer");
        System.out.println("5 : Close Account");
        System.out.println("9 : Debug");
        System.out.println("0 : Quit");
        System.out.println("___________________________");
        System.out.println(" ");
        int i = scan.nextInt();
        scan.nextLine();

        return i;
    }

    public static void transfer() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Transfer");
        System.out.println("___________________________");
        System.out.println("Enter Account Number for Source Account");
        int accountNumberOne = scan.nextInt(); //Gather source account
        scan.nextLine();
        System.out.println("Enter Source Account PIN");
        int pin = scan.nextInt();
        scan.nextLine();
        System.out.println("Enter Account Number to Deposit Into");
        int accountNumberTwo = scan.nextInt(); //Gather Destination account
        scan.nextLine();
        System.out.println("Enter Transfer Amount"); //Gather Transfer amount
        double transfer = scan.nextDouble();
        scan.nextLine();

        for (int j = 0; j < accountArrayList.size(); j++) {
            int acctCheck = accountArrayList.get(j).getAccountNumber();
            if (acctCheck == accountNumberOne && pin == accountArrayList.get(j).getPin()) {
                accountArrayList.get(j).setAccountBalance(accountArrayList.get(j).getAccountBalance() - transfer); //Subtract transfer amount from source account
            } else { System.out.println("Account Number or PIN incorrect"); }

            for (j = 0; j < accountArrayList.size(); j++) {
                acctCheck = accountArrayList.get(j).getAccountNumber();
                if (acctCheck == accountNumberTwo) {
                    accountArrayList.get(j).setAccountBalance(accountArrayList.get(j).getAccountBalance() + transfer); //Add transfer amount to destination account
                }
            }
        }
    }

    public static void closeAccount() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Close Account");
        System.out.println("___________________________");
        System.out.println("Choose Account Number to Close");
        int accountToClose = scan.nextInt();
        scan.nextLine();

        for (int j = 0; j < accountArrayList.size(); j++) {
            int acctCheck = accountArrayList.get(j).getAccountNumber();
            if (acctCheck == accountToClose) {
                accountArrayList.remove(j);
            }
        }
    }
}

class Account {
    private String name;
    private int accountNumber;
    private int pin;
    private double accountBalance;

    public Account(String name, int accountNumber, int pin, double accountBalance) {
        this.name = name;
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.accountBalance = accountBalance;
    }

    public String getName() {
        return this.name;
    }

    public int getAccountNumber() {
        return this.accountNumber;
    }

    public double getAccountBalance() {
        return this.accountBalance;
    }

    public double getPin() {
        return this.pin;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public String toString() {
        return (name + "'s Account \n Account Number " + accountNumber + "\n Account Pin " + pin + "\n Account Balanace " + accountBalance + "\n");
    }
}