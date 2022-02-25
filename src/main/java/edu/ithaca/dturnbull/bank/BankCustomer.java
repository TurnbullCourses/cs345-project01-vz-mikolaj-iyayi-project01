package edu.ithaca.dturnbull.bank;

import java.util.MissingResourceException;

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
    /**
     * deposits amount into checking account unless it's frozen
     * @param amount
     * @throws FrozenException
     */
    public void depositChecking(double amount) throws FrozenException {
        if(checking == null){
            throw new NullPointerException("There is no checking account for this customer"); 
        }
        else{checking.deposit(amount);}
    
    }
    /**
     * deposits amount into savings account unless account is frozen
     * @param amount
     * @throws FrozenException
     */
    public void depositSavings(double amount) throws FrozenException{
        if(savings == null){
            throw new NullPointerException("There is no savings account for this customer"); 
        }
        
        savings.deposit(amount);
    }
    /**
     * withdraws amount from savings account unless account is frozen
     * @param amount
     * @throws FrozenException
     * @throws InsufficientFundsException
     */
    public void withdrawSavings(double amount) throws FrozenException, InsufficientFundsException{
        if(savings == null){
            throw new NullPointerException("There is no savings account for this customer"); 
        }
        
        savings.withdraw(amount);
    }
    /**
     * withdraws amount from checking account unless account is frozen
     * @param amount
     * @throws FrozenException
     * @throws InsufficientFundsException
     */
    public void withdrawChecking(double amount) throws FrozenException, InsufficientFundsException{
        if(checking == null){
            throw new NullPointerException("There is no checking account for this customer"); 
        }
        
        checking.withdraw(amount);
    }
    /**
     * transfers funds from the checking account to another account, unless the account is frozen
     * @param amount
     * @param transferee
     * @throws FrozenException
     * @throws InsufficientFundsException
     */
    public void transferChecking(double amount, BankAccount transferee) throws FrozenException, InsufficientFundsException{
        if(checking == null){
            throw new NullPointerException("There is no checking account for this customer"); 
        }
        
        checking.transfer(amount, transferee);
    }
    /**
     * transfers funds from the savings account to another account, unless the account is frozen
     * @param amount
     * @param transferee
     * @throws FrozenException
     * @throws InsufficientFundsException
     */
    public void transferSavings(double amount, BankAccount transferee) throws FrozenException, InsufficientFundsException{
        if(savings == null){
            throw new NullPointerException("There is no savings account for this customer"); 
        }
        
        savings.transfer(amount, transferee);
    }
    /**
     * returns the current balance on the checking balance
     * @return
     */
    public double getCheckingBalance() {
        if(checking == null){
            throw new NullPointerException("There is no checking account for this customer"); 
        }
        return checking.getBalance();
    }
    /**
     * returns the current balance on the savings account
     * @return
     */
    public double getSavingsBalance() {
    return savings.getBalance();
    }
    /**
     * returns the first name of the customer
     * @return
     */
    public String getFirstName() {
        return firstName;
    }
    /**
     * returns the last name of the customer
     * @return
     */
    public String getLastName() {
        return lastName;
    }
    /**
     * returns the customer's id
     * @return
     */
    public int getId() {
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
        } 
        if (email.indexOf('@') == -1){
            return false;
        }
        return true;
    }

    
    
    /**
     * Transfers funds from the client's checking to his savings account
     * @param amount
     * @throws InsufficientFundsException if the amount would overdraw the checking account
     */
    public void checkingToSavings(double amount) throws InsufficientFundsException, FrozenException{
        if(checking == null){
            throw new NullPointerException("There is no checking account for this customer"); 
        }
        if(savings == null){
            throw new NullPointerException("There is no savings account for this customer"); 
        }
        checking.transfer(amount, savings);
    }

    /**
     * transfers funds from the client's savings to his checking account
     * @param amount
     * @throws InsufficientFundsException if the amount would overdraw the savings account
     */
    public void savingsToChecking(double amount) throws InsufficientFundsException, FrozenException{
        if(checking == null){
            throw new NullPointerException("There is no checking account for this customer"); 
        }
        if(savings == null){
            throw new NullPointerException("There is no savings account for this customer"); 
        }
        savings.transfer(amount, checking);
    }

}
