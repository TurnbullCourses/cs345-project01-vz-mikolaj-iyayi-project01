package edu.ithaca.dturnbull.bank;

public class Admin {
    public Admin(){
    
    }
    /**
     * freezes and limits the customer's interaction with their bank accounts
     * @param customer
     */
    public static void freezeAccount(BankCustomer customer){
        customer.frozen = true;
    }

    /**
     * if the customer's account is frozen, it enables all bank account features
     * @param customer
     */
    public static void unfreezeAccount(BankCustomer customer){
        customer.frozen = false;
    }

    
}
