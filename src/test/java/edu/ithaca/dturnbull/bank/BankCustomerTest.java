package edu.ithaca.dturnbull.bank;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class BankCustomerTest {

    @Test
    public void savingsCheckingTest() throws InsufficientFundsException, FrozenException{

        //system test
        BankCustomer client = new BankCustomer("Penny", "Heinsfield", 32519293, "penny89@gmail.com", 728);
        client.addCheckingAccount(new CheckingAccount(1000));
        client.addSavingsAccount(new SavingsAccount(0.05, 0));

        assertThrows(InsufficientFundsException.class, () -> client.savingsToChecking(500)); //unit test
        assertThrows(IllegalArgumentException.class, () -> client.savingsToChecking(-5)); //unit test

        client.checkingToSavings(30);
        assertEquals(30, client.getSavingsBalance());  //unit test
        assertEquals(970, client.getCheckingBalance()); //unit test

        client.savingsToChecking(15);
        assertEquals(15, client.getSavingsBalance()); //unit test
        assertEquals(985, client.getCheckingBalance()); //unit test
    }

    
}
