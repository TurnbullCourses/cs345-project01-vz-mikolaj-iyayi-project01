package edu.ithaca.dturnbull.bank;

public abstract class Teller {
    
    private BankCustomer customer;

    public Teller (BankCustomer customerIn) {
        customer = customerIn;
    }

    public void deposit(BankAccount account, double amount){

    }

    public void withdraw(BankAccount account, double amount){

    }

    public void transfer(BankAccount accountFrom, BankAccount accountTo, double amount){

    }

    public void accHistory(BankAccount account){

    }

    public boolean confirmUser(BankAccount account){
        return false;
    }

    public double getBalance(){
    //    return customer.getCheckingBalance();
    }

    
}
