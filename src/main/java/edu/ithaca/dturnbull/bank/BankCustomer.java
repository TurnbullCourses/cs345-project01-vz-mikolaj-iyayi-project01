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
    protected boolean status;

    public BankCustomer(String firstName, String lastName, int accountID, String email, int id, boolean status) {
        if (!isEmailValid(email)) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.accountID = accountID;
            this.email = email;
            this.id = id;
            savings = null;
            checking = null;
            this.status = false;
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

    public void depositChecking(double amount) {
    checking.deposit(amount);
    }

    public void depositSavings(double amount) {
    savings.deposit(amount);
    }
    public double getCheckingBalance() {
    return checking.getBalance();
    }

    public double getSavingsBalance() {
    return savings.getBalance();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAccountId() {
        return id;
    }
    /**
     * Checks for validity of email (must have format a@b.cc)
     * @param email
     * @return
     */
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

    
    
    /**
     * Transfers funds from the client's checking to his savings account
     * @param amount
     * @throws InsufficientFundsException if the amount would overdraw the checking account
     */
    public void checkingToSavings(double amount) throws InsufficientFundsException{
        checking.withdraw(amount);
        savings.deposit(amount);
    }

    /**
     * transfers funds from the client's savings to his checking account
     * @param amount
     * @throws InsufficientFundsException if the amount would overdraw the savings account
     */
    public void savingsToChecking(double amount) throws InsufficientFundsException{
        savings.withdraw(amount);
        checking.deposit(amount);
    }

}
