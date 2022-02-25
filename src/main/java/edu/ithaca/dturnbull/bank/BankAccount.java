package edu.ithaca.dturnbull.bank;

/**
 * BankAccount
 * represents a customers account and operations
 * @author Vaibhav Zaveri
 * @date 2/25/2022
 */

public abstract class BankAccount {
    
    private double balance;
    protected boolean frozen;
    

    /**
     * @throws IllegalArgumentException if email is invalid
     */
    public BankAccount(double startingBalance) throws IllegalArgumentException{
        if(!isAmountValid(startingBalance)){
            throw new IllegalArgumentException("invalid amount entered");
        }
            this.balance = startingBalance;
            frozen = false;
        
    }

    /**
     * returns the current balance in the bank account
     * @return
     */
    public double getBalance(){
        return balance;
    }

    

    /**
     * @post reduces the balance by amount if amount is non-negative and smaller than balance
     */
    public void withdraw (double amount) throws InsufficientFundsException, FrozenException{
        if(frozen){
            throw new FrozenException("This account has been frozen");
        }
        
        if(!isAmountValid(amount)){
            throw new IllegalArgumentException("amount cannot be negative or have more than 2 decimal places");

        }
        else if (amount <= balance){
            balance -= amount;
        }
        else {
            throw new InsufficientFundsException("Not enough money");
        }
    }

    /**
     * Increases account balance by amount
     * @param amount
     * @throws IllegalArgumentException if amount is invalid
     */
    public void deposit(double amount) throws IllegalArgumentException, FrozenException{
        if(frozen){
            throw new FrozenException("This account has been frozen");
        }
        
        if (!isAmountValid(amount)) {
            throw new IllegalArgumentException("amount cannot be negative or have more than 2 decimal places");
        }
        else {
            balance = balance + amount;
        } 
    }

    /**verifies the validity of amount
    * @return true if the amount is positive and has two decimal points or less, and false otherwise
    */
    public static boolean  isAmountValid(double amount){
        String doubleStr = Double.toString(amount);

        if(amount < 0){
            return false;
            
        }
        else if(doubleStr.substring(doubleStr.lastIndexOf('.'), doubleStr.length() - 1).length() > 2){ //check if amount has 5 or more digits (possibility that there is 3 decimals) 300.67 , 30.678
            return false;
        }
        else{
            return true;
        }


    }
    
    /**
     * transfers amount between 2 different accounts
     * @param amount
     * @param account
     * @throws InsufficientFundsException
     * @throws IllegalArgumentException
     */
    
    public void transfer(double amount, BankAccount account) throws InsufficientFundsException, IllegalArgumentException, FrozenException{
        if(frozen){
            throw new FrozenException("This account has been frozen");
        }
        else if(account.frozen){
            throw new FrozenException("the transferee's account has been frozen");
        }
        
        if(!isAmountValid(amount)){
            throw new IllegalArgumentException("Amount Invalid");
        }
        else{
           withdraw(amount);
            account.deposit(amount); //account to which we trasfer to
        }
    
    }
    /**
     *calculate and add interest to the balance
     * @param interestRate 
     */
    public void calcInterest(double interestRate){
        if(!isAmountValid(interestRate)){
            throw new IllegalArgumentException("Amount Invalid");
        }
        else{
        double interest = this.balance * interestRate/100.0;
        this.balance = this.balance + interest;
        }
    }

    

    
    
    

   
}