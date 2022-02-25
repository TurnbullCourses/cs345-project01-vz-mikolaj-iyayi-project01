/**
 * Admin
 * represents functions of an Admin
 * @author Vaibhav Zaveri
 * @date 2/25/2022
 */

package edu.ithaca.dturnbull.bank;

public class Admin {
    public Admin(){
    
    }
    /**
     * freezes the bank account
     * @param account
     */
    public static void freezeAccount(BankAccount account){
        account.frozen = true;
    }

    /**
     * if the customer's account is frozen, it enables all bank account features
     * @param account
     */
    public static void unfreezeAccount(BankAccount account){
        account.frozen = false;
    }

    
}
