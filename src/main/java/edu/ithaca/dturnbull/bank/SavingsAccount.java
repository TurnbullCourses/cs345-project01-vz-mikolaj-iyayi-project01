package edu.ithaca.dturnbull.bank;

/**
 * CheckingAccount
 * This class extends BankAccount class
 * @author Mikolaj
 * @date 2/25/2022
 */

public class SavingsAccount extends BankAccount {

    private double interest;

    public SavingsAccount(double interestIn, double startingBalance) {
        
        super(startingBalance);
        interest = interestIn;
    }
    
}
