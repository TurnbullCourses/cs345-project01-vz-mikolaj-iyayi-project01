package edu.ithaca.dturnbull.bank;

public class SavingsAccount extends BankAccount {

    private double interest;
    private final int DAILY_MAX = 1000;

    public SavingsAccount(double interestIn, double balanceIn) {
        super(balanceIn);
        interest = interestIn;
    }

    /**
     *calculate and add interest to the balance
     * @param interestRate 
     */
    public void addInterest(double interestRate){
        if(!isAmountValid(interestRate)){
            throw new IllegalArgumentException("Amount Invalid");
        }
        else{
            interest = this.balance * interestRate/100.0;
            this.balance = this.balance + interest;
        }
    }

    public double getInterest(){
        return interest;
    }
    
}
