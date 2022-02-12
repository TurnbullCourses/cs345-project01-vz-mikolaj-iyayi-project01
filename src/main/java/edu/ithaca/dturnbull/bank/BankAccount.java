package edu.ithaca.dturnbull.bank;

public abstract class BankAccount {

    
    private double balance;
    

    /**
     * @throws IllegalArgumentException if email is invalid
     */
    public BankAccount(double startingBalance) throws IllegalArgumentException{
        if(!isAmountValid(startingBalance)){
            throw new IllegalArgumentException("invalid amount entered");
        }
            this.balance = startingBalance;
        
    }

    public double getBalance(){
        return balance;
    }

    

    /**
     * @post reduces the balance by amount if amount is non-negative and smaller than balance
     */
    public void withdraw (double amount) throws InsufficientFundsException{
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


    public void deposit(double amount) throws IllegalArgumentException {
        if (!isAmountValid(amount)) {
            throw new IllegalArgumentException("amount cannot be negative or have more than 2 decimal places");
        }
        else {
            balance = balance + amount;
        } 
    }

    /**
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
    
    
    // The following method will look at transfering amount between 2 customers
    public void transferAmount(BankAccount account, double amount) throws InsufficientFundsException, IllegalArgumentException{
        if(!isAmountValid(amount)){
            throw new IllegalArgumentException("Amount Invalid");
        }
        else{
           withdraw(amount);
            account.deposit(amount); //account to which we trasfer to
        }
    
    }
     //The following method will look at transfering between checkings and savings account of the customer
     public void transferamt(int transferType, double amount) throws InsufficientFundsException {
        if (transferType == 1) { //checking->saving
            checking.withdraw(amount);
            savings.deposit(amount);
                
             
        } else if (transferType == 2) { //savings->checking
            savings.withdraw(amount);
            checking.deposit(amount);
               
            
        }
    }

   
}