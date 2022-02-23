package edu.ithaca.dturnbull.bank;

public class Admin {
    public Admin(){
    
    }

    public static void freezeAccount(BankCustomer customer){
        customer.frozen = true;
    }
    public static void unfreezeAccount(BankCustomer customer){
        customer.frozen = false;
    }

    
}
