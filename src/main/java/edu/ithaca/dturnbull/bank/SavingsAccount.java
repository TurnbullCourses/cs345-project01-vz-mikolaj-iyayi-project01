package edu.ithaca.dturnbull.bank;

public class SavingsAccount extends BankAccount {

    private double interest;

    public SavingsAccount(double interestIn, double startingBalance) {
        
        super(startingBalance);
        interest = interestIn;
    }
    
}
