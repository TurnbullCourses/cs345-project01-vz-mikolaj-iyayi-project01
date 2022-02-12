package edu.ithaca.dturnbull.bank;

/**
 * BankCustomer
 * This class will help users to make, manage and look over their accounts
 */
public class BankCustomer {

    private String firstName;
    private String lastName;
    private int accountID;
    private String email;
    public int id;
    private SavingsAccount savings;
    private CheckingAccount checking;

    public BankCustomer(String firstName, String lastName, int accountID, String email, int id) {
        if (!isEmailValid(email)) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.accountID = accountID;
            this.email = email;
            this.id = id;
            savings = null;
            checking = null;
        } else {
            throw new IllegalArgumentException("Invalid email parameter");
        }
    }

    /**
     * Adds a new savings account to the bank customer account
     * 
     * @param savings a new savings account
     */
    public void addSavingsAccount(SavingsAccount savings) {
        this.savings = savings;
    }

    /**
     * Adds a new checking account to the bank customer account
     * 
     * @param checking a new checking account
     */
    public void addCheckingAccount(CheckingAccount checking) {
        this.checking = checking;
    }

    // public void depositChecking(double amount) {
    // checking.deposit(amount);
    // }

    // public void depositSavings(double amount) {
    // savings.deposit(amount);
    // }

    // public boolean withdrawChecking(double amount) {
    // return checking.withdraw(amount);
    // }

    // public boolean withdrawSavings(double amount) {
    // return savings.withdraw(amount);
    // }
    // public double getCheckingBalance() {
    // return checking.getBalance();
    // }

    // public double getSavingsBalance() {
    // return savings.getBalance();
    // }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAccountId() {
        return id;
    }

    public static boolean isEmailValid(String email) {
        if (email.indexOf('@') == -1) {
            return false;
        } else if (email.length() <= 3) {
            return false;
        } else if (email.isEmpty()) {
            return false;
        } else if (email.indexOf('@') == 0 || email.indexOf('.') == 0) {
            return false;
        } else if (!Character.isLetter(email.charAt(email.indexOf('@') - 1))) {
            return false;
        } else if (email.contains("$") || email.contains("!") || email.contains("#")) {
            return false;
        } else if (email.charAt(email.indexOf('.')) == email.charAt(email.indexOf('.') + 1)) {
            return false;
        } else if (email.lastIndexOf('.') + 2 >= email.length()) {
            return false;
        } else {
            return true;
        }
    }

    // The following method will look at transfering between checkings and savings
    // account of the customer
    // like transferToChecking and transferToSavings

    public void transferToChecking(SavingsAccount savingsFrom, CheckingAccount checkingsTo, double amount)
            throws InsufficientFundsException, IllegalArgumentException {
        if (!BankAccount.isAmountValid(amount)) {
            throw new IllegalArgumentException("Amount Invalid");
        } else {
            savingsFrom.withdraw(amount);
            checkingsTo.deposit(amount);
        }

    }

    public void transferToSavings(CheckingAccount checkingsFrom, SavingsAccount savingsTo, double amount)
            throws InsufficientFundsException, IllegalArgumentException {
        if (!BankAccount.isAmountValid(amount)) {
            throw new IllegalArgumentException("Amount Invalid");
        } else {
            checkingsFrom.withdraw(amount);
            savingsTo.deposit(amount);
        }

    }
}
